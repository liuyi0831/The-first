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
			System.out.println("进入implement");
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
		System.out.println("impl中的用户余额" + account);
		int seats = price.getFlightSeats();// 新机票剩余座位数
		// 将总价与原价进行对比
		// 当总价大于原价时需要从用户余额中扣取,若是小于则退回多余的钱到用户余额中
		// 总价大于原价,即要从用户余额中扣款
		int diff1 = total - order.getAmount();// 差价
		// 当订单原状态为订购失败时依旧扣款
		if (!"订购失败，余额不足".equals(order.getStatement())) {// 当原来的订单扣过款时
			if (account > diff1) {// 账户余额充足时
				System.out.println("扣过款");
				if (seats > (adt + kid)) {
					user.setUserAccount(account - diff1);// 扣款
					// 退回原来的机票
					Price price2 = priceser.findBypriceId(order.getPriceId());// 找到原来的机票信息
					int seat = price2.getFlightSeats() + order.getKidPsgnum() + order.getAdtPsgnum();
					price2.setFlightSeats(seat);
					price.setFlightSeats(seats - adt - kid);// 座位数减少
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
			System.out.println("没扣过");
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
		return orderser.updateOrder(order);
		} else {
			return 0;
		}
	}

	@Override
	public int returnTicket(User user, Order order, Price price) {
		// TODO Auto-generated method stub
	Long long1=time(price);
		if (long1 > 2) {// 在起飞前两小时才可以改签或退票
			int man = order.getAdtPsgnum() + order.getKidPsgnum();
			int seats = price.getFlightSeats() + man;
			price.setFlightSeats(seats);
			priceser.updatePrice(price);// 座位数增加
			order.setStatement("已退票");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			order.setOrderTime(sdf.format(date));//更新订单信息
			orderser.updateOrder(order);// 更新订单状态
			int account = order.getAmount() + user.getUserAccount();
			user.setUserAccount(account);
			return userser.updateUser(user);// 退回扣款
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
