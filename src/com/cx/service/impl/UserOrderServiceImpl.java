package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.UserOrderMapper;
import com.cx.pojo.UserOrder;
import com.cx.service.UserOrderService;
@Service
public class UserOrderServiceImpl implements UserOrderService {
@Autowired
private UserOrderMapper userordermap;
	@Override
	public List<UserOrder> getOrders(String userid) {
		// TODO Auto-generated method stub
		return userordermap.getAll(userid);
	}

	@Override
	public List<UserOrder> findBysearch(String userid,String orderid, String flightftime, String ordertime) {
		// TODO Auto-generated method stub
		return userordermap.getBysearch(userid,orderid, flightftime, ordertime);
	}

}
