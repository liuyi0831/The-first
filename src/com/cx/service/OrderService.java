package com.cx.service;

import java.util.List;

import com.cx.pojo.Order;

public interface OrderService {
	int insertOrder(Order order);
	int deleteOrder(String orderid);
	int updateOrder(Order order);
	List<Order> findByuserid(String userid);
	Order findByorderid(String orderid);
}
