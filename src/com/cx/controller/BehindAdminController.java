package com.cx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cx.pagelist.PageInfo;
import com.cx.pojo.Admin;
import com.cx.service.AdminService;

@Controller
public class BehindAdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/adminlogin")
	public String adminlogin(Admin admin, Model model,HttpServletRequest request) {
		Admin admin2 = adminService.login(admin);
		if (admin2 != null) {
			HttpSession session=request.getSession();
			session.setAttribute("aname", admin2.getAdminName());
			session.setAttribute("adminid", admin2.getAdminId());
			session.setAttribute("apower", admin.getAdminPower());
			return "behind/index";
		} else {
			model.addAttribute("news", "用户名或密码错误！");
			return "behind/login";
		}

	}
	
	@RequestMapping("/behindloginout")
	public String behindloginout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "behind/login";
	}
	
	@RequestMapping("/alladmin")
	public ModelAndView alladmin(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 3;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int currentResult = (currentPage - 1) * pageSize;//从第几个开始取
        PageInfo page = new PageInfo();
        page.setShowCount(pageSize);
        page.setCurrentResult(currentResult);
        List<Admin> list = adminService.getAll(page);
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
        ModelAndView mav = new ModelAndView("behind/admin-list");
        mav.addObject("admin",list);
        mav.addObject("page", page);
        mav.addObject("pageStr", pageStr);
        mav.addObject("current", currentPage);
        return mav;
	}

	@RequestMapping("/editadmin")
	public String deladmin(String aid,Model model) {
		Admin admin=adminService.findByid(aid);
		model.addAttribute("admin", admin);
		return "behind/admin-edit";
	}
	
	@RequestMapping("/editpower")
	@ResponseBody
	public String editpower(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		Admin login=adminService.findByid((String)session.getAttribute("adminid"));
		System.out.println(login);
		System.out.println("login:"+login.getAdminPassword());
		System.out.println("pass"+request.getParameter("pass"));
		if (login.getAdminPassword().equals(request.getParameter("pass"))) {
		String power=request.getParameter("power");
		Admin admin=adminService.findByid(request.getParameter("id"));
		admin.setAdminPower(power);
		adminService.updateAdmin(admin);
		return "修改成功";
		}
		return "修改失败";
	}

	@RequestMapping("/addadmin")
	public void addadmin(String admin) {
		String []msg=admin.split(",");
		String name=msg[0];
		String pass=msg[1];
		String power=msg[2];
		Admin admin1=new Admin();
		admin1.setAdminId(UUIDUtil.randomUUID());
		admin1.setAdminName(name);admin1.setAdminPassword(pass);
		admin1.setAdminPower(power);
		adminService.insertAdmin(admin1);
	}

	@RequestMapping("/deladmin")
	public String  deladmin(String id) {
		String ids[]=id.split(",");
		if (ids.length>1) {
			for (String string : ids) {
				System.out.println(string);
				adminService.deleteAdmin(string);
			}
		}
		else {
			adminService.deleteAdmin(id);
		}
		return "behind/admin-list";
	}
}

