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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.pojo.Flight;
import com.cx.pojo.Order;
import com.cx.pojo.Passenger;
import com.cx.pojo.Price;
import com.cx.pojo.User;
import com.cx.pojo.UserOrder;
import com.cx.service.FlightService;
import com.cx.service.OrderService;
import com.cx.service.PassengerService;
import com.cx.service.PriceService;
import com.cx.service.UpdateOrderService;
import com.cx.service.UserOrderService;
import com.cx.service.UserService;

@Controller
public class UserOrderController {
	@Autowired
	private UserService userser;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserOrderService userorderser;
	@Autowired
	private PassengerService passser;
	@Autowired
	private FlightService flightser;
	@Autowired
	private PriceService priceser;
	@Autowired
	private UpdateOrderService updateser;
	
	@RequestMapping("/orderdetail")
	public String orderdetail(String id, Model model) {// 查看订单详情
		String[] ids = id.split(",");
		Order order = orderService.findByorderid(ids[0]);
		Price price=priceser.findBypriceId(order.getPriceId());
		Flight flight = flightser.findByid(ids[1]);
		List<Passenger> list = passser.getByOrderid(ids[0]);
		model.addAttribute("flight", flight);
		model.addAttribute("order", order);
		model.addAttribute("psgs", list);
		model.addAttribute("price", price);
		return "user/order_Detail";
	}

	@RequestMapping("/searchorder") // 根据条件查找订单
	public String serach(String order_code, String ftime, String otime, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		List<UserOrder> list = userorderser.findBysearch(id, order_code, ftime, otime);
		model.addAttribute("orders", list);
		return "user/order_List";
	}

	@RequestMapping("/orderedit")
	public String orderedit(String id, Model model, HttpServletRequest request) {// 编辑订单详情
		HttpSession session = request.getSession();
		String[] ids = id.split(",");// 得到订单id和航班id
		session.setAttribute("orderid", ids[0]);// 保存订单编号
		session.setAttribute("priceid",orderService.findByorderid(ids[0]).getPriceId());
		Price price=priceser.findBypriceId(orderService.findByorderid(ids[0]).getPriceId());//通过订单编号得到priceID从而得到价格信息
		Flight flight = flightser.findByid(ids[1]);
		List<Passenger> list = passser.getByOrderid(ids[0]);
		model.addAttribute("flight", flight);
		model.addAttribute("psgs", list);
		model.addAttribute("price", price);
		model.addAttribute("statement", orderService.findByorderid(ids[0]).getStatement());
		return "user/order_edit";
	}

	@RequestMapping("/psgchange") // 更改航班查看该日其他航班信息
	public String psgchange(String flid, Model model) {
		List<Flight> list = flightser.findSingletrip(flightser.findByid(flid).getFlightFrom(),
				flightser.findByid(flid).getFlightArr(), flightser.findByid(flid).getFlightFtime());
		model.addAttribute("flights", list);

		return "user/Flight_List";
	}

	
	@RequestMapping("/userfliseat") // 点击查看想更改的航班的座位及票价
	public String userfliseat(String flightid, Model model) {
		List<Price> price = priceser.findByFlId(flightid);
		model.addAttribute("prices", price);
		return "user/flight_Seat";
	}

	@RequestMapping("/updateflight") // 确认要更改的航班，并返回到编辑订单页面
	public String orderupdate(String priceid, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("priceid", priceid);// 保存选择的航班信息
		String orderid = (String) session.getAttribute("orderid");
		Price price = priceser.findBypriceId(priceid);
		Flight flight = flightser.findByid(price.getFlightId());// 得到航班信息
		List<Passenger> passengers = passser.getByOrderid(orderid);// 得到乘客信息
		model.addAttribute("psgs", passengers);
		model.addAttribute("flight", flight);
		model.addAttribute("price", price);
		return "user/order_edit";
	}

	@RequestMapping("/orderaddpsg") // 确认添加该订单下常用旅客信息
	public String useraddpsg(HttpServletRequest request, Model model,String priceid) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		List<Passenger> list = passser.getByuserid(userid);
		model.addAttribute("psgs", list);
		model.addAttribute("priceid", priceid);
		return "user/add_passenger";
	}

	@RequestMapping("/updatepsg")// 添加完需要的旅客后返回编辑页面
	public String updatepsg(String psgid, Model model, HttpServletRequest request,String priceid) {
		Passenger passenger = passser.getBypsgId(psgid);
		HttpSession session = request.getSession();
		String orderid = (String) session.getAttribute("orderid");//得到订单号
		Price price=priceser.findBypriceId(priceid);//得到价格信息
		Flight flight=flightser.findByid(price.getFlightId());//得到航班信息
		List<Passenger> list = passser.getByOrderid(orderid);
		list.add(passenger);
		model.addAttribute("psgs", list);
		//session.setAttribute("psgs", list);
		model.addAttribute("flight", flight);
		model.addAttribute("price", price);
		return "user/order_edit";

	}

	@RequestMapping(value="/orderupdate",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String orderupdate(HttpServletRequest request,String psgids) {//改签航班
		HttpSession session=request.getSession();
		//得到userid，priceID，orderID
		String userid=(String) session.getAttribute("userid");
		String priceid=(String) session.getAttribute("priceid");
		String orderid=(String) session.getAttribute("orderid");
		String[] psgid=psgids.split(",");//得到旅客id
		//得到用户信息
		User user=userser.getUserById(userid);
		Price price=priceser.findBypriceId(priceid);
		Order order=orderService.findByorderid(orderid);
		order.setPriceId(priceid);
		int adt=0;int kid=0;
		for (int i = 0; i < psgid.length; i++) {
			Passenger passenger=passser.getBypsgId(psgid[i]);
			if ("成年".equals(passenger.getPassenType())) {
				adt++;
			}
			else {
				kid++;
			}
		}
		updateser.updateOrder(user, price, order, adt, kid);
		return order.getStatement();
	}

	@RequestMapping(value="/deleteorder",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String deleteorder(String id) {//删除订单
		String[] s=id.split(",");
		int i=orderService.deleteOrder(s[0]);
		if (i>0) {
			return "删除成功";
		}
		else {
			return "删除失败";
		}
	}

	@RequestMapping("/userdeletepsg")
	public String userdeletepsg(String psgid,Model model, HttpServletRequest request) {//删除乘客
		HttpSession session=request.getSession();
		//得到userid，priceID，orderID
		String priceid=(String) session.getAttribute("priceid");
		Price price=priceser.findBypriceId(priceid);
		Flight flight=flightser.findByid(price.getFlightId());
		model.addAttribute("flight", flight);
		model.addAttribute("price", price);
		String orderid=(String) session.getAttribute("orderid");
		List<Passenger>list=passser.getByOrderid(orderid);
		Passenger passenger=passser.getBypsgId(psgid);
		model.addAttribute("statement", orderService.findByorderid(orderid).getStatement());
		int i=0;
		for (Passenger passenger1 : list) {
			i++;
			if (passenger.equals(passenger1)) {
				list.remove(i);
			}
		}
		
		for (Passenger passenger2 : list) {
			System.out.println(passenger2);
		}
		model.addAttribute("psgs", list);
		return "user/order_edit";
	}
	
	@RequestMapping(value="/retickit",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String retickit(HttpServletRequest request) {//退票
		HttpSession session=request.getSession();
		//得到userid，priceID，orderID
		String userid=(String) session.getAttribute("userid");
		String priceid=(String) session.getAttribute("priceid");
		String orderid=(String) session.getAttribute("orderid");
		User user=userser.getUserById(userid);//得到用户信息
		Price price=priceser.findBypriceId(priceid);
		
		Order order=orderService.findByorderid(orderid);
		int i=updateser.returnTicket(user, order, price);
		if (i>0) {
			return "退票成功！";
		}
		else {
			return "退票失败！";
		}
	}
	int updateOrder(User user, Price price, Order order, int adt, int kid) {//改签
		// TODO Auto-generated method stub
		if (time(price)>2) {//起飞前两小时可改签
		// 计算总价：
		int total = (int) (price.getFlightPrice() * (adt + kid * 0.8));
		// 存入订单信息
		order.setAdtPsgnum(adt);
		order.setKidPsgnum(kid);
		order.setAmount(total);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrderTime(sdf.format(date));
		int account = user.getUserAccount();// 用户余额
		int seats = price.getFlightSeats();// 新机票剩余座位数
		// 将总价与原价进行对比
		// 当总价大于原价时需要从用户余额中扣取,若是小于则退回多余的钱到用户余额中
		// 总价大于原价,即要从用户余额中扣款
		int diff1 = total - order.getAmount();// 差价
		// 当订单原状态为订购失败时依旧扣款
		if (!"订购失败，余额不足".equals(order.getStatement())) {// 当原来的订单扣过款时
			if (account > diff1) {// 账户余额充足时
				if (seats > (adt + kid)) {
					user.setUserAccount(account - diff1);// 扣款
					// 退回原来的机票
					Price price2 = priceser.findBypriceId(order.getPriceId());// 找到原来的机票信息
					int seat = price2.getFlightSeats() + order.getKidPsgnum() + order.getAdtPsgnum();
					price2.setFlightSeats(seat);//增加原来航班的座位数
					price.setFlightSeats(seats - adt - kid);// 当前更改的航班的座位数减少
					userser.updateUser(user);// 用户更新
					priceser.updatePrice(price2);
					priceser.updatePrice(price);// 价格座位更新
					order.setStatement("订购成功");
				} else {
					order.setStatement("订购中");
				}
			} else if (account < diff1) {// 账户余额不足
				order.setStatement("订购失败，余额不足");
			}
		} else {
		
			if (account > total) {
				if (seats > (adt + kid)) {
					user.setUserAccount(account - total);
					price.setFlightSeats(seats - adt - kid);
					userser.updateUser(user);// 用户更新
					priceser.updatePrice(price);// 价格座位更新
					order.setStatement("订购成功");
				} else {
					order.setStatement("订购中");
					
				}
			} else {
				order.setStatement("订购失败，余额不足");
			}
		}
		return orderService.updateOrder(order);
		} else {
			return 0;
		}
	}
	public long time(Price price) {
		Flight flight = flightser.findByid(price.getFlightId());// 得到航班信息，已确认是不是在退票时间之内
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
