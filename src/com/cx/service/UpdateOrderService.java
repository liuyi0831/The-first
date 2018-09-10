package com.cx.service;

import com.cx.pojo.Order;
import com.cx.pojo.Price;
import com.cx.pojo.User;

public interface UpdateOrderService {
int bookTicket(User user,Price price,Order order,int adt,int kid,String[] psgs);//¶©¹º»úÆ±
int updateOrder(User user,Price price,Order order,int adt,int kid);//ÐÞ¸Ä¶©µ¥ÐÅÏ¢£¬¸ÄÇ©º½°à
int returnTicket(User user,Order order,Price price);//ÍËÆ±
}
