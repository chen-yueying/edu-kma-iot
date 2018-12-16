package edu.kma.iot.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.kma.iot.dao.UserDAO;
import edu.kma.iot.dao.model.User;
@Controller
public class AcountController {
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/login")
	public ModelAndView login(Principal	principal, @RequestParam(value = "error", required = false) String error) {
		if(principal != null) return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView("/Home/login");
		if(error != null) mv.addObject("error", error);
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(Principal principal){
		if(principal != null) return new ModelAndView("redirect:/");
		ModelAndView mv = new ModelAndView("/Home/register", "info", new User());
		return mv;
	}
	
	@RequestMapping("/acount/details")
	public ModelAndView UserDetails(Principal principal) {
		ModelAndView mv = new ModelAndView("/User/user.details");
		User user = userDAO.getUser(principal.getName());
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value="/acount-save", method = RequestMethod.POST)
	public ModelAndView UserSave(@Valid @ModelAttribute("info") User user, BindingResult result, HttpServletRequest request) throws ServletException {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/Home/register", "info", user);
			mv.addObject("errors", result);
			return mv;
		}
		if(userDAO.getUser(user.getUsername()) != null) {
			ModelAndView mv = new ModelAndView("/Home/register", "info", user);
			mv.addObject("message", "Tài khoản đã tồn tại!");
			return mv;
		}
		userDAO.Insert(user);
		request.login(user.getUsername(), user.getPassword());
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/acount-update", method = RequestMethod.POST)
	public ModelAndView UserUpdate(Principal principal, @Valid @ModelAttribute("info") User user, BindingResult result, @RequestParam(value = "oldPass", required=false) String oldPass) {
		if(result.hasErrors()) {
			ModelAndView mv = UserEdit(principal);
			mv.addObject("info", user);
			mv.addObject("errors", result);
			mv.addObject("message", "Nhập không hợp lệ!");
			return mv;
		}
		if(!userDAO.getUser(principal.getName()).getPassword().equals(oldPass)) {
			ModelAndView mv = UserEdit(principal);
			mv.addObject("errorpassword", "Mật khẩu cũ không đúng!");
			return mv;
		}
		userDAO.update(user);
		ModelAndView mv = UserDetails(principal);
		mv.addObject("message", "Cập nhập thành công!");
		return mv;
	}
	
	@RequestMapping("/acount/edit")
	public ModelAndView UserEdit(Principal principal) {
		User user = userDAO.getUser(principal.getName());
		user.setPassword(null);
		ModelAndView mv = new ModelAndView("/User/user.edit", "info", user);
		return mv;
	}
	
}
