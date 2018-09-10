package com.cx.service;



import java.util.List;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.Flight;

public interface FlightService {
int insertFlight(Flight flight);
int deleteFlight(String id);
int updateFlight(Flight flight);
List<Flight> findRoundtrip(String flightFrom,String flightArr,String flightFtime,String flightBtime,PageInfo pageInfo);//��ҳ��ѯ��������
List<Flight> findSingletrip(String flightFrom,String flightArr,String flightFtime,PageInfo pageInfo);//��ҳ��ѯ���̺���
List<Flight> getAll(PageInfo pageInfo);
Flight findByid(String id);
List<Flight> findRoundtrip(String flightFrom,String flightArr,String flightFtime,String flightBtime);//��������
List<Flight> findSingletrip(String flightFrom,String flightArr,String flightFtime);//���̺���
}
