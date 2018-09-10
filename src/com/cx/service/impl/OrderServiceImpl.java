package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.OrderMapper;
import com.cx.pojo.Order;
import com.cx.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
@Autowired
OrderMapper ordermap;
	@Override
	public int insertOrder(Order order) {
		// TODO Auto-generated method stub
		return ordermap.insert(order);
	}

	@Override
	public int deleteOrder(String orderid) {
		// TODO Auto-generated method stub
		return ordermap.deleteByPrimaryKey(orderid);
	}

	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return ordermap.updateByPrimaryKey(order);
	}

	@Override
	public List<Order> findByuserid(String userid) {
		// TODO Auto-generated method stub
		return ordermap.selectByUserid(userid);
	}

	@Override
	public Order findByorderid(String orderid) {
		// TODO Auto-generated method stub
		return ordermap.selectByPrimaryKey(orderid);
	}

}
