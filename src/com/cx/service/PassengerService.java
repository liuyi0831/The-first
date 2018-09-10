package com.cx.service;

import java.util.List;

import com.cx.pojo.Passenger;

public interface PassengerService {
int insertPassenger(Passenger passenger);
int deletePassenger(String psgerId);
int updatePassenger(Passenger passenger);
List<Passenger> getAll();
List<Passenger> getByuserid(String userId);
List<Passenger> getBysearch(String search);//根据条件模糊查询旅客姓名
List<Passenger> getByOrderid(String orderid);//查询同一订单下的旅客信息
Passenger getBypsgId(String passenId);
}
