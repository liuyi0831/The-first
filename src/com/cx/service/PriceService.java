package com.cx.service;

import java.util.List;

import com.cx.pojo.Price;

public interface PriceService {
int insertPrice(Price price);
int deletePrice(String priceId);
int updatePrice(Price price);
Price findBypriceId(String priceId);
List<Price> findByFlId(String flightId);
List<Price> getAll();
int getmixByflId(String flightid);
}
