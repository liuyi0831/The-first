package com.cx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cx.pojo.UserOrder;

public interface UserOrderMapper {
	List<UserOrder> getAll(String userid);// 获取该用户的所有订单
	List<UserOrder> getBysearch(@Param("user_id")String userid,@Param("order_id")String orderid,@Param("flight_ftime")String flightftime,@Param("order_time")String orderime);// 通过输入的条件对订单进行查询

}
