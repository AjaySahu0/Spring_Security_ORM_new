package com.ind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ind.entity.UserEntity;
import com.ind.sevice.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegister() {
		return "userRegister";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute UserEntity user, Model model) {

		Integer id = userService.saveUser(user);
		String message = "User " + id + " created";
		model.addAttribute("message", message);
		return "userRegister";

	}

}
