package com.bookshop.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.entity.guest.WebGuest;
import com.bookshop.bookshop.service.UserServiceInterface;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserServiceInterface userService;

	@Autowired
	public RegistrationController(UserServiceInterface userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	

    @PostMapping("/registration")
	public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody WebGuest webguest, BindingResult theBindingResult,	HttpSession session) {
		
		String userName = webguest.getUserName();
		if (theBindingResult.hasErrors()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User existing = userService.findByUserName(userName);
		if (existing != null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userService.save(webguest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
