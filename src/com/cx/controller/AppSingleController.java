package com.cx.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.pojo.Flight;
import com.cx.pojo.Order;
import com.cx.pojo.Passenger;
import com.cx.pojo.Price;
import com.cx.pojo.User;
import com.cx.service.FlightService;
import com.cx.service.OrderService;
import com.cx.service.PassengerService;
import com.cx.service.PriceService;
import com.cx.service.UpdateOrderService;
import com.cx.service.UserService;

@Controller
public class AppSingleController {
	@Autowired
	private UserService userser;

	@Autowired
	private FlightService fService;

	@Autowired
	private PriceService priceser;

	@Autowired
	private PassengerService psgerser;
	
	@Autowired
	private UpdateOrderService updateorder;
	
	@RequestMapping("/single") // 具体航班信息
	public String single(String id, Model model) {
		System.out.println("航班信息" + fService.findByid(id));
		Flight flight = fService.findByid(id);
		List<Price> list = priceser.findByFlId(id);
		for (Price price : list) {
			System.out.println(price);
		}
		model.addAttribute("flight", flight);
		model.addAttribute("prices", list);
		return "main/p-single";
	}

	@RequestMapping("/book") //点击预订将该航班信息提交到后台，之后再由客户选择添加旅客信息
	public String bookticket(String priceid, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("uname");//判断是否登录
		if (name != null) {//已登录则跳转到添加旅客界面，将priceID传到下一个页面
			List<Passenger> list = psgerser.getByuserid((String)session.getAttribute("userid"));//获取该用户的常用旅客信息
			int i=list.size();
			model.addAttribute("priceid", priceid);//将票价信息传到下一个页面
			model.addAttribute("psgnum", i);//一个用户做多添加六个常用旅客信息
			model.addAttribute("passengers", list);//用户已存在的旅客信息
			return "main/passengers";
		} else {
			return "main/booking";
		}

	}
	
	@RequestMapping("/getdetail")//查看原有旅客具体信息
	@ResponseBody
	public Passenger Detail(String psgid) {
		Passenger passenger=psgerser.getBypsgId(psgid);
		return passenger;
	}

	@RequestMapping("/addpsg")//添加新的旅客信息
	@ResponseBody
	public String test(@ModelAttribute Passenger passenger, HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		passenger.setPassenId(UUIDUtil.randomUUID());
		passenger.setOrderId("未预定");//默认添加新用户时orderID为未预定
		User user = userser.getUserById((String)session.getAttribute("userid"));
		passenger.setUserId(user.getUserId());
		if (psgerser.insertPassenger(passenger) > 0) {
			return "ok";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/order")//确认人数及旅客信息产生订单
	public String book(String id,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("userid");
		User user=userser.getUserById(userid);//获取当前用户信息
		String[]ids=id.split(",");//最后一个为priceID
		Price price=priceser.findBypriceId(ids[ids.length-1]);//获取该航班信息
		//统计已选择的旅客不同类型的人数
		int adt=0;int kid=0;
		for (int i = 0; i < ids.length-1; i++) {
			if ("成年".equals(psgerser.getBypsgId(ids[i]).getPassenType())) {
				adt++;
			}
			else {
				kid++;
			}
		}
		//产生新订单
		Order order=new Order();
		order.setPriceId(ids[ids.length-1]);
		order.setUserId(userid);
		int msg=bookTicket(user, price, order, adt, kid,ids);
		/*if (msg<ids.length) {
			model.addAttribute("news", "插入失败！");
		}
		else {*/
			model.addAttribute("news", order.getStatement());
		
		return "main/booking";
	}
	@Autowired
	private OrderService orderser;
	@RequestMapping("/ordering")
	public String ordering(String email,String orderid,Model model) {//无需登录查看预定机票
		String userid=userser.getUserByemail(email).getUserId();
		Order order=orderser.findByorderid(orderid);
		if (order.getUserId().equals(userid)) {
			List<Passenger>list=psgerser.getByOrderid(orderid);
			Price p=priceser.findBypriceId(order.getPriceId());
			Flight flight=fService.findByid(p.getFlightId());
			model.addAttribute("psg", list);
			model.addAttribute("order", order);
			model.addAttribute("flight", flight);
			
		}
		else {
			model.addAttribute("order", "不存在该用户，或不存在该订单");
		}
		return "main/withoutlogin";
	}
	
	int bookTicket(User user, Price price, Order order, int adt, int kid,String[] psgs) {
		// TODO Auto-generated method stub
		if (time(price)>2) {//起飞前两小时可订票
		// 计算总价：
		int total = (int) (price.getFlightPrice() * (adt + kid * 0.8));
		// 存入订单信息
		order.setAdtPsgnum(adt);
		order.setKidPsgnum(kid);
		order.setAmount(total);
		order.setOrderId(UUIDUtil.randomUUID());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrderTime(sdf.format(date));
		// 将票价与账户余额作对比
		int account = user.getUserAccount() - total;
		int seats = price.getFlightSeats() - (adt + kid);
		if (account > 0) {
			if (seats > 0) {// 当用户余额大于总价时并且该航班余票充足
				user.setUserAccount(account);
				userser.updateUser(user);// 用户账户扣款
				price.setFlightSeats(seats);
				priceser.updatePrice(price);// 该航班座位减少
				String state = "订购成功";
				order.setStatement(state);
			} else {
				String state = "订购中";
				order.setStatement(state);
			}
		} else if (account < 0) {
			String state = "订购失败，余额不足";
			order.setStatement(state);
		}
		int m=0;
		orderser.insertOrder(order);
		for (int i = 0; i < psgs.length-1; i++) {//添加已生成订单的旅客信息
			Passenger passenger=psgerser.getBypsgId(psgs[i]);
			passenger.setOrderId(order.getOrderId());
			passenger.setPassenId(UUIDUtil.randomUUID());
			m+=psgerser.insertPassenger(passenger);
		}
		return m;
		}else {
			return 0;
		}
	}
	public long time(Price price) {
		Flight flight = fService.findByid(price.getFlightId());// 得到航班信息，已确认是不是在退票时间之内
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date ftime = null;
		try {
			ftime = sdf.parse(flight.getFlightFtime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 得到航班起飞时间
		Date date = new Date();// 得到当前时间
		Long long1 = (ftime.getTime() - date.getTime()) / (60 * 60 * 1000);// 相差时间
		return long1;
	}
}
