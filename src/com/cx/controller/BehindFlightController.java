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
import com.cx.pojo.Flight;
import com.cx.pojo.Price;
import com.cx.service.FlightService;
import com.cx.service.PriceService;

@Controller
public class BehindFlightController {
	@Autowired
	private FlightService flightService;
	@Autowired
	private PriceService priservice;

	@RequestMapping("/flightlist")
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
		List<Flight> list = flightService.getAll(page);
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
		ModelAndView mav = new ModelAndView("behind/flight-list");
		mav.addObject("flight", list);
		mav.addObject("page", page);
		mav.addObject("pageStr", pageStr);
		mav.addObject("current", currentPage);
		return mav;
	}

	@RequestMapping("/flightseat")
	public String flightseat(String flightid, Model model) {
		List<Price> list = priservice.findByFlId(flightid);
		model.addAttribute("seats", list);
		model.addAttribute("fliid", flightid);
		return "behind/flight-view";
	}

	@RequestMapping("/seatedit")
	public String seatedit(String seatid, Model model) {
		Price price = priservice.findBypriceId(seatid);
		String flightName = flightService.findByid(price.getFlightId()).getFlightName();
		model.addAttribute("price", price);
		model.addAttribute("fliname", flightName);
		return "behind/seat-edit";
	}

	@RequestMapping("/addseat")
	public String addseat(String flightid,Price price,Model model) {
		model.addAttribute("fliid", flightid);
		System.out.println("pricezhong:"+price.getFlightId());
		price.setPriceId(UUIDUtil.randomUUID());
		int i=priservice.insertPrice(price);
		if (i>0) {
			model.addAttribute("backnews", "添加成功！");
			return "behind/seat-add";
		}
		return "behind/seat-add";
	}
	@RequestMapping(value="/editseat",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String editseat(Price price) {
		String flightid = priservice.findBypriceId(price.getPriceId()).getFlightId();
		price.setFlightId(flightid);
		priservice.updatePrice(price);
		return "修改成功";
	}

	@RequestMapping("/flightedit")
	public String flightedit(String flightid, Model model) {
		Flight flight = flightService.findByid(flightid);
		model.addAttribute("flight", flight);
		return "behind/flight-edit";
	}

	@RequestMapping(value="/updateseat",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String updateseat(Flight flight) {
		flightService.updateFlight(flight);
		return "成功";
	}

	@RequestMapping(value="/addflight",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String addflight(Flight flight) {
		flight.setFlightId(UUIDUtil.randomUUID());
		flightService.insertFlight(flight);
		return "添加成功！";
	}

	@RequestMapping("/delflight")
	public String deladmin(String id) {
		String ids[] = id.split(",");
		if (ids.length > 1) {
			for (String string : ids) {
				System.out.println(string);
				flightService.deleteFlight(string);
			}
		} else {
			flightService.deleteFlight(id);
		}
		return "behind/admin-list";
	}
}
