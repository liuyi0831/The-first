package com.cx.service;

import java.util.List;

import com.cx.pojo.UserOrder;

public interface UserOrderService {
List<UserOrder> getOrders(String userid);
List<UserOrder> findBysearch(String userid,String orderid,String flightftime,String ordertime);

}
