package com.cx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.dao.PriceMapper;
import com.cx.pojo.Price;
import com.cx.service.PriceService;
@Service
public class PriceServiceImpl implements PriceService {
@Autowired
private PriceMapper pricemp;
	@Override
	public int insertPrice(Price price) {
		// TODO Auto-generated method stub
		if (price.getFlightId()==null) {
			return 0;
		}
		return pricemp.insert(price);
	}

	@Override
	public int deletePrice(String priceId) {
		// TODO Auto-generated method stub
		return pricemp.deleteByPrimaryKey(priceId);
	}

	@Override
	public int updatePrice(Price price) {
		// TODO Auto-generated method stub
		return pricemp.updateByPrimaryKey(price);
	}

	@Override
	public Price findBypriceId(String priceId) {
		// TODO Auto-generated method stub
		return pricemp.selectByPrimaryKey(priceId);
	}

	@Override
	public List<Price> findByFlId(String flightId) {
		// TODO Auto-generated method stub
		return pricemp.selectByFlid(flightId);
	}

	@Override
	public List<Price> getAll() {
		// TODO Auto-generated method stub
		return pricemp.selectAll();
	}

	@Override
	public int getmixByflId(String flightid) {
		// TODO Auto-generated method stub
		System.out.println("implÖÐµÄprices"+pricemp.selectmixByflId(flightid));
		return pricemp.selectmixByflId(flightid);
	}

}
