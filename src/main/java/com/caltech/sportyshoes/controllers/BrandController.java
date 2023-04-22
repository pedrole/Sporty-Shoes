package com.caltech.sportyshoes.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caltech.sportyshoes.dao.BrandDAO;
import com.caltech.sportyshoes.pojo.Brand;
import com.caltech.sportyshoes.pojo.Product;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	BrandDAO brandDao;
	
	@RequestMapping("/index")
	public ModelAndView display(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("message") String message
			) {
		ModelAndView mv = new ModelAndView();
//		
		Product product = new Product();
		List<Brand> brand = brandDao.getAll();
		mv.addObject("list", brand);
		//if(StringUtils.isNotBlank(message))
		mv.addObject(message);
		mv.setViewName("/brands.jsp");
		return mv;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView();
		Optional<Brand> brand = brandDao.getByid(Integer.parseInt(id));
		
		mv.addObject("brand", brand.orElseThrow());
		mv.setViewName("/editBrand.jsp");
		return mv;
	}
	@PostMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
			 RedirectAttributes attributes,
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="name", required=true) String name
			) {
		
		ModelAndView mv = new ModelAndView("redirect:/brand/index");
		
		Brand brand = new Brand();
		brand.setId(NumberUtils.toInt(id));
		brand.setName(name);
		
		
		
		brandDao.update(brand);
		
		attributes.addFlashAttribute("message","You succefully updated product with id " + brand.getId());
		
		
		return mv;
		
	}
	
	@RequestMapping("/addBrand")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		Product product = new Product();
		List<Brand> brands= brandDao.getAll();
		mv.addObject("brands", brands);
		mv.setViewName("/addBrand.jsp");
		return mv;
	}
	@PostMapping("/addBrand")
	public ModelAndView registerBrand(HttpServletRequest request, HttpServletResponse response,
			 RedirectAttributes attributes ) {
		ModelAndView mv = new ModelAndView("redirect:/brand/index");
		 
		Brand brand = new Brand();
		brand.setName(request.getParameter("name"));
		
		 attributes.addFlashAttribute("message","You succefully inserted brand!");
		
		brandDao.insert(brand);
	
		return mv;
		
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/brand/index");
		brandDao.delete(Integer.parseInt(id));
		return mv;
		
	}
	

}
