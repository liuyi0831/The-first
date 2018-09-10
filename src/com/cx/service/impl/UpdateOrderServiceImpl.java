package com.cx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.controller.UUIDUtil;
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

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService {
	@Autowired
	private UserService userser;
	@Autowired
	private PriceService priceser;
	@Autowired
	private OrderService orderser;
	@Autowired
	private FlightService flightser;
	@Autowired
	private PassengerService passer;

	@Override
	public int bookTicket(User user, Price price, Order order, int adt, int kid,String[] psgs) {
		// TODO Auto-generated method stub
		System.out.println("inplment");
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
			System.out.println("����implement");
			Passenger passenger=passer.getBypsgId(psgs[i]);
			passenger.setOrderId(order.getOrderId());
			passenger.setPassenId(UUIDUtil.randomUUID());
			m+=passer.insertPassenger(passenger);
		}
		return m;
		}else {
			return 0;
		}
	}

	@Override
	public int updateOrder(User user, Price price, Order order, int adt, int kid) {
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
		System.out.println("impl�е��û����" + account);
		int seats = price.getFlightSeats();// �»�Ʊʣ����λ��
		// ���ܼ���ԭ�۽��жԱ�
		// ���ܼ۴���ԭ��ʱ��Ҫ���û�����п�ȡ,����С�����˻ض����Ǯ���û������
		// �ܼ۴���ԭ��,��Ҫ���û�����пۿ�
		int diff1 = total - order.getAmount();// ���
		// ������ԭ״̬Ϊ����ʧ��ʱ���ɿۿ�
		if (!"����ʧ�ܣ�����".equals(order.getStatement())) {// ��ԭ���Ķ����۹���ʱ
			if (account > diff1) {// �˻�������ʱ
				System.out.println("�۹���");
				if (seats > (adt + kid)) {
					user.setUserAccount(account - diff1);// �ۿ�
					// �˻�ԭ���Ļ�Ʊ
					Price price2 = priceser.findBypriceId(order.getPriceId());// �ҵ�ԭ���Ļ�Ʊ��Ϣ
					int seat = price2.getFlightSeats() + order.getKidPsgnum() + order.getAdtPsgnum();
					price2.setFlightSeats(seat);
					price.setFlightSeats(seats - adt - kid);// ��λ������
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
			System.out.println("û�۹�");
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
		return orderser.updateOrder(order);
		} else {
			return 0;
		}
	}

	@Override
	public int returnTicket(User user, Order order, Price price) {
		// TODO Auto-generated method stub
	Long long1=time(price);
		if (long1 > 2) {// �����ǰ��Сʱ�ſ��Ը�ǩ����Ʊ
			int man = order.getAdtPsgnum() + order.getKidPsgnum();
			int seats = price.getFlightSeats() + man;
			price.setFlightSeats(seats);
			priceser.updatePrice(price);// ��λ������
			order.setStatement("����Ʊ");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			order.setOrderTime(sdf.format(date));//���¶�����Ϣ
			orderser.updateOrder(order);// ���¶���״̬
			int account = order.getAmount() + user.getUserAccount();
			user.setUserAccount(account);
			return userser.updateUser(user);// �˻ؿۿ�
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
