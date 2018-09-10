package com.cx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.User;
import com.cx.service.UserService;

@Controller
public class BehindMemberControl {
	@Autowired
	private UserService uService;

	@RequestMapping("/memberlist")
	public ModelAndView alladmin(HttpServletRequest request, HttpServletResponse response) {
		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		int pageSize = 3;
		if (currentPage <= 0) {
			currentPage = 1;
		}
		int currentResult = (currentPage - 1) * pageSize;// 从第几个开始取
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		List<User> list = uService.getUsers(page);
		int totalCount = page.getTotalResult();// 得到总数total
		int lastPage = 0;
		if (totalCount % pageSize == 0) {
			lastPage = totalCount / pageSize;
		} else {
			lastPage = 1 + totalCount / pageSize;// 总页数
		}
		if (currentPage >= lastPage) {
			currentPage = lastPage;
		}
		String string = "<a class=\"num\" href=\"" + request.getRequestURI() + "?page=" + (currentPage - 1)
				+ "\">&lt;&lt;</a>";
		StringBuffer pageStr = new StringBuffer();
		pageStr.append(string);
		for (int i = 1; i < lastPage + 1; i++) {
			if (i == currentPage) {
				pageStr.append("<span class=\"current\">" + i + "</span></a>");
			} else {
				pageStr.append(
						"<a class=\"num\" href=\"" + request.getRequestURI() + "?page=" + i + "\">" + i + "</a>");
			}
		}
		pageStr.append("<a class=\"num\" href=\"" + request.getRequestURI() + "?page=" + (currentPage + 1)
				+ "\">&gt;&gt;</a>");
		// 制定视图，也就是list.jsp
		ModelAndView mav = new ModelAndView("behind/member-list");
		mav.addObject("user", list);
		mav.addObject("page", page);
		mav.addObject("pageStr", pageStr);
		mav.addObject("current", currentPage);
		return mav;

	}

	@RequestMapping("/behindadduser")
	public void behindadduser(String user) {
		String[] msg = user.split(",");
		User user2 = new User();
		user2.setUserId(UUIDUtil.randomUUID());
		user2.setUserName(msg[0]);
		user2.setUserEmail(msg[1]);
		user2.setUserPhone(msg[2]);
		user2.setUserPassword(msg[3]);
		uService.insertUser(user2);
	}

	@RequestMapping("/deluser")
	public String deluser(String id) {
		String ids[]=id.split(",");
		if (ids.length>1) {
			for (String string : ids) {
				System.out.println(string);
				uService.deleteUserById(string);
			}
		}
		else {
			uService.deleteUserById(id);
		}
		return "behind/admin-list";
	}

	@RequestMapping("/behindeditUser")
	public String beidtuser(String userid,Model model) {
		User user=uService.getUserById(userid);
		model.addAttribute("user", user);
		return "behind/member-edit";
	}
	@RequestMapping(value="/beidtuser",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String beidtuser(User user) {
		user.setUserPassword(uService.getUserById(user.getUserId()).getUserPassword());;
		uService.updateUser(user);
		return " <label for=\"L_email\" class=\"layui-form-label\">修改成功</label>";
		
	}
	
	@RequestMapping(value="/beadduser",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String beadduser(User user) {
		user.setUserId(UUIDUtil.randomUUID());
		uService.insertUser(user);
		return "success";
	}
	
}
