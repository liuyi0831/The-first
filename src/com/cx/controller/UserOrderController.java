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
	public String orderdetail(String id, Model model) {// �鿴��������
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

	@RequestMapping("/searchorder") // �����������Ҷ���
	public String serach(String order_code, String ftime, String otime, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		List<UserOrder> list = userorderser.findBysearch(id, order_code, ftime, otime);
		model.addAttribute("orders", list);
		return "user/order_List";
	}

	@RequestMapping("/orderedit")
	public String orderedit(String id, Model model, HttpServletRequest request) {// �༭��������
		HttpSession session = request.getSession();
		String[] ids = id.split(",");// �õ�����id�ͺ���id
		session.setAttribute("orderid", ids[0]);// ���涩�����
		session.setAttribute("priceid",orderService.findByorderid(ids[0]).getPriceId());
		Price price=priceser.findBypriceId(orderService.findByorderid(ids[0]).getPriceId());//ͨ��������ŵõ�priceID�Ӷ��õ��۸���Ϣ
		Flight flight = flightser.findByid(ids[1]);
		List<Passenger> list = passser.getByOrderid(ids[0]);
		model.addAttribute("flight", flight);
		model.addAttribute("psgs", list);
		model.addAttribute("price", price);
		model.addAttribute("statement", orderService.findByorderid(ids[0]).getStatement());
		return "user/order_edit";
	}

	@RequestMapping("/psgchange") // ���ĺ���鿴��������������Ϣ
	public String psgchange(String flid, Model model) {
		List<Flight> list = flightser.findSingletrip(flightser.findByid(flid).getFlightFrom(),
				flightser.findByid(flid).getFlightArr(), flightser.findByid(flid).getFlightFtime());
		model.addAttribute("flights", list);

		return "user/Flight_List";
	}

	
	@RequestMapping("/userfliseat") // ����鿴����ĵĺ������λ��Ʊ��
	public String userfliseat(String flightid, Model model) {
		List<Price> price = priceser.findByFlId(flightid);
		model.addAttribute("prices", price);
		return "user/flight_Seat";
	}

	@RequestMapping("/updateflight") // ȷ��Ҫ���ĵĺ��࣬�����ص��༭����ҳ��
	public String orderupdate(String priceid, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("priceid", priceid);// ����ѡ��ĺ�����Ϣ
		String orderid = (String) session.getAttribute("orderid");
		Price price = priceser.findBypriceId(priceid);
		Flight flight = flightser.findByid(price.getFlightId());// �õ�������Ϣ
		List<Passenger> passengers = passser.getByOrderid(orderid);// �õ��˿���Ϣ
		model.addAttribute("psgs", passengers);
		model.addAttribute("flight", flight);
		model.addAttribute("price", price);
		return "user/order_edit";
	}

	@RequestMapping("/orderaddpsg") // ȷ����Ӹö����³����ÿ���Ϣ
	public String useraddpsg(HttpServletRequest request, Model model,String priceid) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		List<Passenger> list = passser.getByuserid(userid);
		model.addAttribute("psgs", list);
		model.addAttribute("priceid", priceid);
		return "user/add_passenger";
	}

	@RequestMapping("/updatepsg")// �������Ҫ���ÿͺ󷵻ر༭ҳ��
	public String updatepsg(String psgid, Model model, HttpServletRequest request,String priceid) {
		Passenger passenger = passser.getBypsgId(psgid);
		HttpSession session = request.getSession();
		String orderid = (String) session.getAttribute("orderid");//�õ�������
		Price price=priceser.findBypriceId(priceid);//�õ��۸���Ϣ
		Flight flight=flightser.findByid(price.getFlightId());//�õ�������Ϣ
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
	public String orderupdate(HttpServletRequest request,String psgids) {//��ǩ����
		HttpSession session=request.getSession();
		//�õ�userid��priceID��orderID
		String userid=(String) session.getAttribute("userid");
		String priceid=(String) session.getAttribute("priceid");
		String orderid=(String) session.getAttribute("orderid");
		String[] psgid=psgids.split(",");//�õ��ÿ�id
		//�õ��û���Ϣ
		User user=userser.getUserById(userid);
		Price price=priceser.findBypriceId(priceid);
		Order order=orderService.findByorderid(orderid);
		order.setPriceId(priceid);
		int adt=0;int kid=0;
		for (int i = 0; i < psgid.length; i++) {
			Passenger passenger=passser.getBypsgId(psgid[i]);
			if ("����".equals(passenger.getPassenType())) {
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
	public String deleteorder(String id) {//ɾ������
		String[] s=id.split(",");
		int i=orderService.deleteOrder(s[0]);
		if (i>0) {
			return "ɾ���ɹ�";
		}
		else {
			return "ɾ��ʧ��";
		}
	}

	@RequestMapping("/userdeletepsg")
	public String userdeletepsg(String psgid,Model model, HttpServletRequest request) {//ɾ���˿�
		HttpSession session=request.getSession();
		//�õ�userid��priceID��orderID
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
	public String retickit(HttpServletRequest request) {//��Ʊ
		HttpSession session=request.getSession();
		//�õ�userid��priceID��orderID
		String userid=(String) session.getAttribute("userid");
		String priceid=(String) session.getAttribute("priceid");
		String orderid=(String) session.getAttribute("orderid");
		User user=userser.getUserById(userid);//�õ��û���Ϣ
		Price price=priceser.findBypriceId(priceid);
		
		Order order=orderService.findByorderid(orderid);
		int i=updateser.returnTicket(user, order, price);
		if (i>0) {
			return "��Ʊ�ɹ���";
		}
		else {
			return "��Ʊʧ�ܣ�";
		}
	}
	int updateOrder(User user, Price price, Order order, int adt, int kid) {//��ǩ
		// TODO Auto-generated method stub
		if (time(price)>2) {//���ǰ��Сʱ�ɸ�ǩ
		// �����ܼۣ�
		int total = (int) (price.getFlightPrice() * (adt + kid * 0.8));
		// ���붩����Ϣ
		order.setAdtPsgnum(adt);
		order.setKidPsgnum(kid);
		order.setAmount(total);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrderTime(sdf.format(date));
		int account = user.getUserAccount();// �û����
		int seats = price.getFlightSeats();// �»�Ʊʣ����λ��
		// ���ܼ���ԭ�۽��жԱ�
		// ���ܼ۴���ԭ��ʱ��Ҫ���û�����п�ȡ,����С�����˻ض����Ǯ���û������
		// �ܼ۴���ԭ��,��Ҫ���û�����пۿ�
		int diff1 = total - order.getAmount();// ���
		// ������ԭ״̬Ϊ����ʧ��ʱ���ɿۿ�
		if (!"����ʧ�ܣ�����".equals(order.getStatement())) {// ��ԭ���Ķ����۹���ʱ
			if (account > diff1) {// �˻�������ʱ
				if (seats > (adt + kid)) {
					user.setUserAccount(account - diff1);// �ۿ�
					// �˻�ԭ���Ļ�Ʊ
					Price price2 = priceser.findBypriceId(order.getPriceId());// �ҵ�ԭ���Ļ�Ʊ��Ϣ
					int seat = price2.getFlightSeats() + order.getKidPsgnum() + order.getAdtPsgnum();
					price2.setFlightSeats(seat);//����ԭ���������λ��
					price.setFlightSeats(seats - adt - kid);// ��ǰ���ĵĺ������λ������
					userser.updateUser(user);// �û�����
					priceser.updatePrice(price2);
					priceser.updatePrice(price);// �۸���λ����
					order.setStatement("�����ɹ�");
				} else {
					order.setStatement("������");
				}
			} else if (account < diff1) {// �˻�����
				order.setStatement("����ʧ�ܣ�����");
			}
		} else {
		
			if (account > total) {
				if (seats > (adt + kid)) {
					user.setUserAccount(account - total);
					price.setFlightSeats(seats - adt - kid);
					userser.updateUser(user);// �û�����
					priceser.updatePrice(price);// �۸���λ����
					order.setStatement("�����ɹ�");
				} else {
					order.setStatement("������");
					
				}
			} else {
				order.setStatement("����ʧ�ܣ�����");
			}
		}
		return orderService.updateOrder(order);
		} else {
			return 0;
		}
	}
	public long time(Price price) {
		Flight flight = flightser.findByid(price.getFlightId());// �õ�������Ϣ����ȷ���ǲ�������Ʊʱ��֮��
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
