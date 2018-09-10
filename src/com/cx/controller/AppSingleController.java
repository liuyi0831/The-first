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
	
	@RequestMapping("/single") // ���庽����Ϣ
	public String single(String id, Model model) {
		System.out.println("������Ϣ" + fService.findByid(id));
		Flight flight = fService.findByid(id);
		List<Price> list = priceser.findByFlId(id);
		for (Price price : list) {
			System.out.println(price);
		}
		model.addAttribute("flight", flight);
		model.addAttribute("prices", list);
		return "main/p-single";
	}

	@RequestMapping("/book") //���Ԥ�����ú�����Ϣ�ύ����̨��֮�����ɿͻ�ѡ������ÿ���Ϣ
	public String bookticket(String priceid, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("uname");//�ж��Ƿ��¼
		if (name != null) {//�ѵ�¼����ת������ÿͽ��棬��priceID������һ��ҳ��
			List<Passenger> list = psgerser.getByuserid((String)session.getAttribute("userid"));//��ȡ���û��ĳ����ÿ���Ϣ
			int i=list.size();
			model.addAttribute("priceid", priceid);//��Ʊ����Ϣ������һ��ҳ��
			model.addAttribute("psgnum", i);//һ���û�����������������ÿ���Ϣ
			model.addAttribute("passengers", list);//�û��Ѵ��ڵ��ÿ���Ϣ
			return "main/passengers";
		} else {
			return "main/booking";
		}

	}
	
	@RequestMapping("/getdetail")//�鿴ԭ���ÿ;�����Ϣ
	@ResponseBody
	public Passenger Detail(String psgid) {
		Passenger passenger=psgerser.getBypsgId(psgid);
		return passenger;
	}

	@RequestMapping("/addpsg")//����µ��ÿ���Ϣ
	@ResponseBody
	public String test(@ModelAttribute Passenger passenger, HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		passenger.setPassenId(UUIDUtil.randomUUID());
		passenger.setOrderId("δԤ��");//Ĭ��������û�ʱorderIDΪδԤ��
		User user = userser.getUserById((String)session.getAttribute("userid"));
		passenger.setUserId(user.getUserId());
		if (psgerser.insertPassenger(passenger) > 0) {
			return "ok";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/order")//ȷ���������ÿ���Ϣ��������
	public String book(String id,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("userid");
		User user=userser.getUserById(userid);//��ȡ��ǰ�û���Ϣ
		String[]ids=id.split(",");//���һ��ΪpriceID
		Price price=priceser.findBypriceId(ids[ids.length-1]);//��ȡ�ú�����Ϣ
		//ͳ����ѡ����ÿͲ�ͬ���͵�����
		int adt=0;int kid=0;
		for (int i = 0; i < ids.length-1; i++) {
			if ("����".equals(psgerser.getBypsgId(ids[i]).getPassenType())) {
				adt++;
			}
			else {
				kid++;
			}
		}
		//�����¶���
		Order order=new Order();
		order.setPriceId(ids[ids.length-1]);
		order.setUserId(userid);
		int msg=bookTicket(user, price, order, adt, kid,ids);
		/*if (msg<ids.length) {
			model.addAttribute("news", "����ʧ�ܣ�");
		}
		else {*/
			model.addAttribute("news", order.getStatement());
		
		return "main/booking";
	}
	@Autowired
	private OrderService orderser;
	@RequestMapping("/ordering")
	public String ordering(String email,String orderid,Model model) {//�����¼�鿴Ԥ����Ʊ
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
			model.addAttribute("order", "�����ڸ��û����򲻴��ڸö���");
		}
		return "main/withoutlogin";
	}
	
	int bookTicket(User user, Price price, Order order, int adt, int kid,String[] psgs) {
		// TODO Auto-generated method stub
		if (time(price)>2) {//���ǰ��Сʱ�ɶ�Ʊ
		// �����ܼۣ�
		int total = (int) (price.getFlightPrice() * (adt + kid * 0.8));
		// ���붩����Ϣ
		order.setAdtPsgnum(adt);
		order.setKidPsgnum(kid);
		order.setAmount(total);
		order.setOrderId(UUIDUtil.randomUUID());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrderTime(sdf.format(date));
		// ��Ʊ�����˻�������Ա�
		int account = user.getUserAccount() - total;
		int seats = price.getFlightSeats() - (adt + kid);
		if (account > 0) {
			if (seats > 0) {// ���û��������ܼ�ʱ���Ҹú�����Ʊ����
				user.setUserAccount(account);
				userser.updateUser(user);// �û��˻��ۿ�
				price.setFlightSeats(seats);
				priceser.updatePrice(price);// �ú�����λ����
				String state = "�����ɹ�";
				order.setStatement(state);
			} else {
				String state = "������";
				order.setStatement(state);
			}
		} else if (account < 0) {
			String state = "����ʧ�ܣ�����";
			order.setStatement(state);
		}
		int m=0;
		orderser.insertOrder(order);
		for (int i = 0; i < psgs.length-1; i++) {//��������ɶ������ÿ���Ϣ
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
		Flight flight = fService.findByid(price.getFlightId());// �õ�������Ϣ����ȷ���ǲ�������Ʊʱ��֮��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date ftime = null;
		try {
			ftime = sdf.parse(flight.getFlightFtime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �õ��������ʱ��
		Date date = new Date();// �õ���ǰʱ��
		Long long1 = (ftime.getTime() - date.getTime()) / (60 * 60 * 1000);// ���ʱ��
		return long1;
	}
}
