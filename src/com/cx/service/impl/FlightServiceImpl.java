package com.cx.service.impl;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.FlightMapper;
import com.cx.pagelist.PageInfo;
import com.cx.pojo.Flight;
import com.cx.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
@Autowired
	private FlightMapper flightser;

	@Override
	public int insertFlight(Flight flight) {
		// TODO Auto-generated method stub
		return flightser.insert(flight);
	}

	@Override
	public int deleteFlight(String id) {
		// TODO Auto-generated method stub
		return flightser.deleteByPrimaryKey(id);
	}

	@Override
	public int updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		return flightser.updateByPrimaryKey(flight);
	}



	@Override
	public List<Flight> findSingletrip(String flightFrom, String flightArr, String flightFtime) {
		// TODO Auto-generated method stub
		return flightser.selectBydetail(flightFrom, flightArr, flightFtime);
	}

	@Override
	public List<Flight> findRoundtrip(String flightFrom, String flightArr, String flightFtime, String flightBtime) {
		// TODO Auto-generated method stub
		List<Flight>list = new ArrayList<Flight>();
		list.addAll(flightser.selectBydetail(flightFrom, flightArr, flightFtime));
		list.addAll(flightser.selectBydetail(flightArr, flightFrom, flightBtime));
		return list;
	}

	
	@Override
	public List<Flight> getAll(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return flightser.selectAllListPage(pageInfo);
	}

	@Override
	public List<Flight> findSingletrip(String flightFrom, String flightArr, String flightFtime, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return flightser.selectBydetailListPage(flightFrom, flightArr, flightFtime, pageInfo);
	}
	@Override
	public List<Flight> findRoundtrip(String flightFrom, String flightArr, String flightFtime, String flightBtime,PageInfo pageInfo) {
		// TODO Auto-generated method stub
		List<Flight>list=flightser.selectBydetailListPage(flightFrom, flightArr, flightFtime, pageInfo);
		list.addAll(flightser.selectBydetailListPage(flightArr, flightFrom, flightBtime, pageInfo));
		pageInfo.setTotalResult(list.size());
		return list;
	}

	
	@Override
	public Flight findByid(String id) {
		// TODO Auto-generated method stub
		return flightser.selectByPrimaryKey(id);
	}
}
