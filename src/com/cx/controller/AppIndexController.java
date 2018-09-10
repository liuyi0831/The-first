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
	


	@RequestMapping("/login") // �û���¼
	public String login(String email, String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (userserv.userlogin(email, password) != null) {
			session.setAttribute("uname", userserv.userlogin(email, password));// �û�����
			User user = userserv.getUserByemail(email);
			session.setAttribute("userid", user.getUserId());// �û�ID
		} else {
			session.setAttribute("backnews", "��¼�����������");
		}
		return "main/index";
	}

	@RequestMapping("/logout") // �û��ǳ�
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "main/index";
	}

	@RequestMapping("/trip") // ��ѯ������Ʊ
	public ModelAndView alladmin(HttpServletRequest request, HttpServletResponse response) {
		//�õ���������
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
        int currentResult = (currentPage - 1) * pageSize;//�ӵڼ�����ʼȡ
        PageInfo page = new PageInfo();
        page.setShowCount(pageSize);
        page.setCurrentResult(currentResult);
        List<Flight> list = new ArrayList<>();
        if (idea==null) {
        	if (deptime!=null) {
				list = fliserv.findRoundtrip(departure, destination, deptime, destime,page); // ��ѯ������Ʊ
			}
			else {
				list = fliserv.findSingletrip(departure, destination, destime,page);//��ѯ����
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
						(String)session.getAttribute("destime"),page); // ��ѯ������Ʊ
			}
			else {
				list = fliserv.findSingletrip((String)session.getAttribute("depa"), 
						(String)session.getAttribute("destn"),(String)session.getAttribute("destime"),page);//��ѯ����
			}
		}
       
        int totalCount = page.getTotalResult();//�õ�����total
        int lastPage = 0;
        if (totalCount % pageSize == 0) {
            lastPage = totalCount / pageSize;
        } else {
            lastPage = 1 + totalCount / pageSize;//��ҳ��
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
        // �ƶ���ͼ��Ҳ����list.jsp
        ModelAndView mav = new ModelAndView("main/products");
        mav.addObject("list",list);
        mav.addObject("page",page);
        mav.addObject("pageStr", pageStr);
        return mav;
	}

}
