package com.caltech.sportyshoes.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caltech.sportyshoes.dao.BrandDAO;
import com.caltech.sportyshoes.dao.OrderDAO;
import com.caltech.sportyshoes.dao.ProductDAO;
import com.caltech.sportyshoes.dao.UserDAO;
import com.caltech.sportyshoes.pojo.Brand;
import com.caltech.sportyshoes.pojo.MyUsersDetails;
import com.caltech.sportyshoes.pojo.Order;
import com.caltech.sportyshoes.pojo.Product;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	ProductDAO productDao;
	@Autowired
	BrandDAO brandDao;
	@Autowired
	OrderDAO orderDao;
	@Autowired
	UserDAO userDao;

	@RequestMapping("/buy")

	public ModelAndView buy(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/product/index");

		int productId = Integer.parseInt(request.getParameter("id"));
		MyUsersDetails details = (MyUsersDetails) authentication.getPrincipal();

		Product product = productDao.findById(productId).orElseThrow();
		Order order = new Order();
		order.setProduct(product);
		order.setTotal(product.getCost());

		order.setCustomer(userDao.findById(details.getId()).orElseThrow());
		order.setOrderedDate(new Date());
		orderDao.insert(order);
		attributes.addFlashAttribute("message", "You have bought the product " + productId);

		return mv;

	}
	@PostMapping("/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) throws ParseException {
		ModelAndView mv = new ModelAndView("redirect:/order/index");
		
		Date orderedDate = new SimpleDateFormat("yyyy-MM-dd").
				parse(request.getParameter("orderedDate"));
		String brand = request.getParameter("brand");
	
		
		attributes.addAttribute("orderedDate", request.getParameter("orderedDate"));
		attributes.addAttribute("brand",brand);
		
		return mv;
	}

	@RequestMapping("/index")

	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@ModelAttribute("orderedDate") String orderedDate,
			@ModelAttribute("brand") String brand
			) throws ParseException {
		ModelAndView mv = new ModelAndView();
		//Object attribute = te("teste1");
		
		//String parameter = request.getParameter("orderedDate");
		
		
		List<Brand> brands = brandDao.getAll();
		
		 int brandID = NumberUtils.toInt(brand);
		 brandID = brandID == 0 ? (brands.size() > 0 ? brands.get(0).getId()
				 : 0) : brandID ;
		 
		orderedDate = StringUtils.defaultIfBlank(orderedDate,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		 
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(orderedDate);
		 List<Order> orders = orderDao.getOrders( date, brandID);
		
		
		mv.addObject("brands", brands);
		mv.addObject("orderedDate", orderedDate);
		mv.addObject("selectedBrand", brandID);
		mv.addObject("orders",orders);

		mv.setViewName("/orders.jsp");
		return mv;
	}

}
