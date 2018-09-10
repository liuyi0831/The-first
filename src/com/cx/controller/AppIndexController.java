package com.cx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cx.pagelist.PageInfo;

import com.cx.pojo.Flight;

import com.cx.pojo.User;
import com.cx.service.FlightService;

import com.cx.service.UserService;

@Controller
public class AppIndexController {
	@Autowired
	private UserService userserv;

	@Autowired
	private FlightService fliserv;
	


	@RequestMapping("/login") // 用户登录
	public String login(String email, String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (userserv.userlogin(email, password) != null) {
			session.setAttribute("uname", userserv.userlogin(email, password));// 用户姓名
			User user = userserv.getUserByemail(email);
			session.setAttribute("userid", user.getUserId());// 用户ID
		} else {
			session.setAttribute("backnews", "登录名或密码错误！");
		}
		return "main/index";
	}

	@RequestMapping("/logout") // 用户登出
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "main/index";
	}

	@RequestMapping("/trip") // 查询往返机票
	public ModelAndView alladmin(HttpServletRequest request, HttpServletResponse response) {
		//得到搜索条件
		String departure=request.getParameter("departure");
		String destination=request.getParameter("destination");
		String deptime=request.getParameter("deptime");
		String destime=request.getParameter("destime");
		String idea=request.getParameter("idea");
		HttpSession session=request.getSession();
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 3;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int currentResult = (currentPage - 1) * pageSize;//从第几个开始取
        PageInfo page = new PageInfo();
        page.setShowCount(pageSize);
        page.setCurrentResult(currentResult);
        List<Flight> list = new ArrayList<>();
        if (idea==null) {
        	if (deptime!=null) {
				list = fliserv.findRoundtrip(departure, destination, deptime, destime,page); // 查询往返机票
			}
			else {
				list = fliserv.findSingletrip(departure, destination, destime,page);//查询单程
			}
			session.setAttribute("depa",departure);
			session.setAttribute("destn",destination);
			session.setAttribute("dept",deptime);
			session.setAttribute("destime",destime);
		}
		else {
			if (deptime!=null) {
				list = fliserv.findRoundtrip((String)session.getAttribute("depa"), 
						(String)session.getAttribute("destn"), (String)session.getAttribute("dept"), 
						(String)session.getAttribute("destime"),page); // 查询往返机票
			}
			else {
				list = fliserv.findSingletrip((String)session.getAttribute("depa"), 
						(String)session.getAttribute("destn"),(String)session.getAttribute("destime"),page);//查询单程
			}
		}
       
        int totalCount = page.getTotalResult();//得到总数total
        int lastPage = 0;
        if (totalCount % pageSize == 0) {
            lastPage = totalCount / pageSize;
        } else {
            lastPage = 1 + totalCount / pageSize;//总页数
        }
        System.out.println("latpage"+lastPage+"totalcount"+totalCount);
        if (currentPage >= lastPage) {
            currentPage = lastPage;
        }   
        String string="<h4><a href=\""+request.getRequestURI()+"?page="+(currentPage-1)+"\">&lt;&lt;</a>";
        StringBuffer pageStr = new StringBuffer();
        pageStr.append(string);
       for (int i = 1; i < lastPage+1; i++) {
		if (i==currentPage) {
			pageStr.append("<a href=\"#\">"+i+"</a>");
		}
		else {
			pageStr.append("<a  href=\""+request.getRequestURI()+"?page="+i+"\">"+i+"</a>");
		}
	}
       pageStr.append("<a  href=\""+request.getRequestURI()+"?page="+(currentPage+1)+"\">&gt;&gt;</a></h4>");
        // 制定视图，也就是list.jsp
        ModelAndView mav = new ModelAndView("main/products");
        mav.addObject("list",list);
        mav.addObject("page",page);
        mav.addObject("pageStr", pageStr);
        return mav;
	}

}
