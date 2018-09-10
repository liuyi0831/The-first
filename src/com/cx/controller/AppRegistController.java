package com.cx.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.pojo.User;
import com.cx.service.UserService;

@Controller
public class AppRegistController {
	@Autowired
	private UserService uService;

	@RequestMapping(value="/signup",produces="application/json;charset=utf-8")
	public String singup(User user, String repwd,HttpServletRequest request) {
		System.out.println(user);
		HttpSession session=request.getSession();
		if (user.getUserPassword().equals(repwd)) {
			user.setUserId(UUID.randomUUID().toString());
			System.out.println(user.getUserId());
			if (uService.insertUser(user)>0) {
				session.setAttribute("uname", user.getUserName());
				return "index";
			}
			String backnews="ÓÊÏäÒÑ×¢²á";
			session.setAttribute("backnews",backnews );
			return "signup";
		} else {
			return "signup";
		}
	}
}
