package com.cx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.pojo.Passenger;
import com.cx.service.PassengerService;



@Controller
public class UserInfoController {
	@Autowired
	private PassengerService passengerService;

	@RequestMapping("/userinfosearch")
	public String Search(String search,Model model) {
		List<Passenger>list=passengerService.getBysearch(search);
		model.addAttribute("psgs", list);
		return "user/user_Info";
	}
	@RequestMapping("/editpsg") // 编辑旅客信息
	public String edit(String id,Model model) {
		Passenger passenger=passengerService.getBypsgId(id);
		model.addAttribute("psg", passenger);
		return "user/edit_Car";
	}

	@RequestMapping("/updatepsgbyuser")
	@ResponseBody
	public String updatepsg(Passenger psg){
		Passenger passenger=passengerService.getBypsgId(psg.getPassenId());
		psg.setUserId(passenger.getUserId());psg.setOrderId(passenger.getOrderId());
		int i=passengerService.updatePassenger(psg);
		if (i>0) {
			return "OK";
		}
		return "Fail";
	}
	
	@RequestMapping("/deletepsg") // 删除旅客信息
	@ResponseBody
	public String del(String id) {
		int i=passengerService.deletePassenger(id);
		if (i>0) {
			return "OK";
		}
		return "Fail";
	}
	@RequestMapping("/useraddpsg")
	public String adpsg(HttpServletRequest request,Model model) {
		HttpSession session =request.getSession();
		List<Passenger>list=passengerService.getByuserid((String)session.getAttribute("userid"));
		if (list.size()==6) {
			model.addAttribute("news", "一个用户最多添加六个常用旅客！");
		}
		else {
			model.addAttribute("news", "");
		}
		return "user/add_Car";
	}
}
