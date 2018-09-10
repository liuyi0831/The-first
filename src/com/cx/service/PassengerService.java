package com.cx.service;

import java.util.List;

import com.cx.pojo.Passenger;

public interface PassengerService {
int insertPassenger(Passenger passenger);
int deletePassenger(String psgerId);
int updatePassenger(Passenger passenger);
List<Passenger> getAll();
List<Passenger> getByuserid(String userId);
List<Passenger> getBysearch(String search);//��������ģ����ѯ�ÿ�����
List<Passenger> getByOrderid(String orderid);//��ѯͬһ�����µ��ÿ���Ϣ
Passenger getBypsgId(String passenId);
}
