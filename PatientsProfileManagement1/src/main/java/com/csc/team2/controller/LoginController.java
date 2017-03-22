package com.csc.team2.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csc.team2.model.User;
import com.csc.team2.service.IUserService;

@RestController
public class LoginController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities().toString());
		if(auth.getAuthorities().toString().equals("[admin]")){
			User user = userService.findUserByUsername(auth.getName());
			modelAndView.addObject("userName", "Hi " + user.getName());
			modelAndView.setViewName("/admin/home");	
			user.getRolesList();
			return modelAndView;
		}
		else if(auth.getAuthorities().toString().equals("[doctor]")) {
			User user = userService.findUserByUsername(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName());
			modelAndView.setViewName("/doctor/home");
			return modelAndView;
		}
		else if(auth.getAuthorities().toString().equals("[nurse]")) {
			User user = userService.findUserByUsername(auth.getName());
			modelAndView.addObject("userName", "Welcome " + user.getName());
			modelAndView.setViewName("/nurse/home");
			return modelAndView;
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}
		
	/*@RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}*/

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/DoctorRegistration", method = RequestMethod.GET)
	public ModelAndView doctorRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("admin/DoctorRegistration");
		return modelAndView;
	}
	@RequestMapping(value="/admin/NurseRegistration", method = RequestMethod.GET)
	public ModelAndView nurseRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("admin/NurseRegistration");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewAdmin(@Valid User user,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else { 
				userService.saveAdmin(user);
				modelAndView.addObject("successMessage", "User has been registered successfully");
				modelAndView.addObject("user", new User());
				modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/DoctorRegistration", method = RequestMethod.POST)
	public ModelAndView createNewDoctor(@Valid User user,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/DoctorRegistration");
		} else { 
				userService.saveDoctor(user);
				modelAndView.addObject("successMessage", "User has been registered successfully");
				modelAndView.addObject("user", new User());
				modelAndView.setViewName("admin/DoctorRegistration");		
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/NurseRegistration", method = RequestMethod.POST)
	public ModelAndView createNewNurse(@Valid User user,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		//@RequestParam("roles") boolean radioValue, 
		
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/NurseRegistration");
		} else { 
			//if (radioValue){
				userService.saveNurse(user);
				modelAndView.addObject("successMessage", "User has been registered successfully");
				modelAndView.addObject("user", new User());
				modelAndView.setViewName("admin/NurseRegistration");
			//}
//			else{
//				userService.saveNurse(user);
//				modelAndView.addObject("successMessage", "User has been registered successfully");
//				modelAndView.addObject("user", new User());
//				modelAndView.setViewName("admin/registration");
//			}
			
		}
		return modelAndView;
	}
//	
//	@RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Object principal) {
//		principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return principal.toString();
//    }
	
	/*@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		modelAndView.addObject("userName", "Hi " + user.getName());
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("/admin/home");
		return modelAndView;
	}*/
	
	

}