package com.cx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cx.pojo.UserOrder;

public interface UserOrderMapper {
	List<UserOrder> getAll(String userid);// ��ȡ���û������ж���
	List<UserOrder> getBysearch(@Param("user_id")String userid,@Param("order_id")String orderid,@Param("flight_ftime")String flightftime,@Param("order_time")String orderime);// ͨ������������Զ������в�ѯ

}
