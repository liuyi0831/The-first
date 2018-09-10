package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.PassengerMapper;
import com.cx.pojo.Passenger;
import com.cx.service.PassengerService;

@Service
public class PsgerServiceImpl implements PassengerService {
	@Autowired
	private PassengerMapper passengermap;

	@Override
	public int insertPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
			return passengermap.insert(passenger);
	}

	@Override
	public int deletePassenger(String psgerId) {
		// TODO Auto-generated method stub
		return passengermap.deleteByPrimaryKey(psgerId);
	}

	@Override
	public int updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		return passengermap.updateByPrimaryKey(passenger);
	}

	@Override
	public List<Passenger> getAll() {
		// TODO Auto-generated method stub
		return passengermap.selectAll();
	}

	@Override
	public List<Passenger> getByuserid(String userId) {
		// TODO Auto-generated method stub
		return passengermap.selectByUserId(userId);
	}

	@Override
	public Passenger getBypsgId(String passenId) {
		// TODO Auto-generated method stub
		return passengermap.selectByPrimaryKey(passenId);
	}

	@Override
	public List<Passenger> getBysearch(String search) {
		// TODO Auto-generated method stub
		return passengermap.selectBysearch(search);
	}

	@Override
	public List<Passenger> getByOrderid(String orderid) {
		// TODO Auto-generated method stub
		return passengermap.selectByOrderId(orderid);
	}

}
