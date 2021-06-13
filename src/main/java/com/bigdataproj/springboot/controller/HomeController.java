package com.bigdataproj.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bigdataproj.springboot.service.TweetsService;

@Controller
public class HomeController {

	@Autowired
	TweetsService tweetsService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView map = new ModelAndView("home.jsp");
		map.addObject("lists", tweetsService.getTweetsUsers());
		return map;
	}

}

