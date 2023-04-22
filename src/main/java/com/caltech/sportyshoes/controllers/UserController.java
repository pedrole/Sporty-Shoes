package com.caltech.sportyshoes.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caltech.sportyshoes.dao.UserDAO;
import com.caltech.sportyshoes.dao.UserDetailsServiceImplementation;
import com.caltech.sportyshoes.pojo.MyUsersDetails;
import com.caltech.sportyshoes.pojo.User;
import com.caltech.sportyshoes.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDAO userDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserDetailsServiceImplementation userDetailsServiceImplementation;

	@GetMapping("/register")
	public String registerGet() {
		return "register.jsp";
	}

	@PostMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/login");

		User user = new User();
		user.setUsername(request.getParameter("user"));
		bCryptPasswordEncoder.encode(request.getParameter("pwd"));
		user.setPassword(bCryptPasswordEncoder.encode(request.getParameter("pwd")));

		String password = user.getPassword();
		user.setPassword(password);
		user.setRole("CUSTOMER");

		userRepository.save(user);

		return mv;

	}

	@GetMapping("/user/profile")
	public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/profile.jsp");
		return mv;
	}

	@PostMapping("/user/changePassword")
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "confirmPassword", required = true) String confirmPassword,
			Authentication authentication, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/user/profile");
		MyUsersDetails details = (MyUsersDetails) authentication.getPrincipal();
		if (!newPassword.equals(confirmPassword)) {
			attributes.addFlashAttribute("message", "The passwords do not match!");
			return mv;
		}

		if (bCryptPasswordEncoder.matches(password, details.getPassword())) {
			String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
			userDao.updatePassword(details.getId(), encodedPassword);

			UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(authentication.getName());
			authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
					userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			attributes.addFlashAttribute("message", "You successfully changed your password!");

		} else {
			attributes.addFlashAttribute("message", "You entered a wrong password!");
		}

		return mv;

	}

	@GetMapping("/user/index")
	public ModelAndView listUser(HttpServletRequest request, HttpServletResponse response) {

		String username = StringUtils.defaultString(request.getParameter("usernameSearch"));
		ModelAndView mv = new ModelAndView();
		List<User> users = userDao.findByUsernameIsContaining(username);
		mv.addObject("users", users);
		mv.addObject("username", username);

		mv.setViewName("/users.jsp");
		return mv;

	}

}
