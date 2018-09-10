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
import com.cx.pojo.User;
import com.cx.pojo.UserOrder;
import com.cx.service.PassengerService;
import com.cx.service.UserOrderService;
import com.cx.service.UserService;

@Controller
public class UserIndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private PassengerService psgservice;
	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping("/userlist")
	public String getusermsg(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = userService.getUserById((String) session.getAttribute("userid"));
		model.addAttribute("user", user);
		return "user/user_List";
	}

	@RequestMapping("/userinfo")
	public String getpsg(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		List<Passenger> list = psgservice.getByuserid((String) session.getAttribute("userid"));
		model.addAttribute("psgs", list);
		return "user/user_Info";
	}

	@RequestMapping("/useredit")
	public String useredit(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/add_User";
	}

	@RequestMapping("/orderlist")
	public String orderlist(HttpServletRequest request, Model model) {
		System.out.println("进入orderlist");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		List<UserOrder> list=userOrderService.getOrders(id);
		for (UserOrder userOrder : list) {
			System.out.println(userOrder);
		}
		model.addAttribute("orders", list);
		return "user/order_List";
	}
	@RequestMapping(value="/adduser",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String adduser(String pwd, User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");
		String psw = userService.getUserById(id).getUserPassword();
		user.setUserId(id);
		user.setUserAccount(userService.getUserById(id).getUserAccount());
		if (psw.equals(pwd)&&user.getUserPassword()!=null) {
			if (userService.updateUser(user) > 0) {
				return "修改成功！";
			} else {
				return "修改失败！";
			}
		} else {
			return "输入密码错误 ";
		}
	}
	
	@RequestMapping(value="/pwdedit",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String pwdedit(String pwd, String pwd1,HttpServletRequest request) {
		HttpSession session=request.getSession();
		User user=userService.getUserById((String)session.getAttribute("userid"));
		if (pwd.equals(user.getUserPassword())) {
			user.setUserPassword(pwd1);
			if (userService.updateUser(user) > 0) {
				return "修改成功！";
			} else {
				return "修改失败！";
			}
		} else {
			return "输入密码错误 ";
		}
	}
	
	@RequestMapping(value="/checkpwd",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String check(String pwd1,String pwd2) {
		if (pwd1!=null&&pwd2!=null) {
			if (pwd1.equals(pwd2)) {
				return "";
			}
			return "两次输入的密码不一致";
		}
		if (pwd1==null&&pwd2==null) {
			return "";
		} 
		else{
			return "请输入新密码！";
		}
	}
}
