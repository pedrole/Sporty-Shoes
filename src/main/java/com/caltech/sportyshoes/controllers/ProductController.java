package com.caltech.sportyshoes.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caltech.sportyshoes.dao.BrandDAO;
import com.caltech.sportyshoes.dao.ProductDAO;
import com.caltech.sportyshoes.pojo.Brand;
import com.caltech.sportyshoes.pojo.Product;



@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductDAO dao;
	@Autowired
	BrandDAO brandDao;

	
	@RequestMapping("/index")
	public ModelAndView display(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("message") String message) {
		ModelAndView mv = new ModelAndView();
//		
		Product product = new Product();
		List<Product> order = dao.getall();
		mv.addObject("list", order);
		mv.addObject(message);
		mv.setViewName("/products.jsp");
		return mv;
	}
	@RequestMapping("/addProduct")
	public ModelAndView showAddProduct(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		Product product = new Product();
		List<Brand> brands= brandDao.getAll();
		mv.addObject("brands", brands);
		mv.setViewName("/addProduct.jsp");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView();
		Optional<Product> product = dao.findById(Integer.parseInt(id));
		List<Brand> brands = brandDao.getAll();
		mv.addObject("brands", brands);
		mv.addObject("product", product.orElseThrow());
		mv.setViewName("/editProduct.jsp");
		return mv;
	}
	@PostMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
			 RedirectAttributes attributes,
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="brand", required=true) String brand,
			@RequestParam(value="cost", required=true) String cost) {
		
		ModelAndView mv = new ModelAndView("redirect:/product/index");
		
		Product product = new Product();
		product.setId(NumberUtils.toInt(id));
		product.setName(name);
		product.setCost(NumberUtils.toDouble(cost));
		
		product.setBrand(brandDao.getByid(NumberUtils.toInt(brand)).orElseThrow());
		dao.update(product);
		
		attributes.addFlashAttribute("message","You succefully updated product with id " + product.getId());
		
		
		return mv;
		
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/product/index");
		dao.delete(Integer.parseInt(id));
		return mv;
		
	}
	
	@PostMapping("/addProduct")
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/product/index");
		 
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setCost(Double.parseDouble( request.getParameter("cost")));
		int brandId = Integer.parseInt( request.getParameter("brand"));
		Brand brand = brandDao.getByid(brandId).orElse(null);
		
		product.setBrand(brand);
		
		dao.insert(product);
	
		
	
		return mv;
		
	}
	
	
	
}
