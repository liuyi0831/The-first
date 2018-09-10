package com.cx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cx.dao.AdminOrderMapper;
import com.cx.pagelist.PageInfo;

import com.cx.pojo.AdminOrder;

@Controller
public class BehindOrderController {
@Autowired
private AdminOrderMapper adorderser;
@RequestMapping("/beorderlist")
public ModelAndView orderlist(HttpServletRequest request, HttpServletResponse response) {
    int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
    int pageSize = 3;
    if (currentPage <= 0) {
        currentPage = 1;
    }
    int currentResult = (currentPage - 1) * pageSize;//从第几个开始取
    PageInfo page = new PageInfo();
    page.setShowCount(pageSize);
    page.setCurrentResult(currentResult);
    List<AdminOrder> list =adorderser.getAllListPage(page);
    int totalCount = page.getTotalResult();//得到总数total
    int lastPage = 0;
    if (totalCount % pageSize == 0) {
        lastPage = totalCount / pageSize;
    } else {
        lastPage = 1 + totalCount / pageSize;//总页数
    }
    if (currentPage >= lastPage) {
        currentPage = lastPage;
    }   
    String string="<a class=\"num\" href=\""+request.getRequestURI()+"?page="+(currentPage-1)+"\">&lt;&lt;</a>";
    StringBuffer pageStr = new StringBuffer();
    pageStr.append(string);
   for (int i = 1; i < lastPage+1; i++) {
	if (i==currentPage) {
		pageStr.append("<span class=\"current\">"+i+"</span></a>");
	}
	else {
		pageStr.append("<a class=\"num\" href=\""+request.getRequestURI()+"?page="+i+"\">"+i+"</a>");
	}
}
   pageStr.append("<a class=\"num\" href=\""+request.getRequestURI()+"?page="+(currentPage+1)+"\">&gt;&gt;</a>");
    // 制定视图，也就是list.jsp
    ModelAndView mav = new ModelAndView("behind/order-list");
    mav.addObject("orders",list);
    mav.addObject("page", page);
    mav.addObject("pageStr", pageStr);
    mav.addObject("current", currentPage);
    return mav;
}
}
