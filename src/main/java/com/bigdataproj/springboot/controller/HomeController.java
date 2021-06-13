package com.bigdataproj.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigdataproj.springboot.model.TweetsUser;
import com.bigdataproj.springboot.service.TweetsService;

@Controller
public class HomeController {

	@Autowired
	TweetsService tweetsService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Model model) {

		List<TweetsUser> list = new ArrayList<TweetsUser>();
		List<String> tables = tweetsService.getTables();
		model.addAttribute("tables",tables);
		model.addAttribute("lists",list);
		return "home";
	}
	
	@RequestMapping(value = "/list/{tablename}" , method = RequestMethod.GET)
	public String list(Model model,@PathVariable("tablename") String tablename) {

		model.addAttribute("lists", tweetsService.getTweetsUsers());
		List<String> tables = tweetsService.getTables();
		model.addAttribute("tablename", tablename);
		model.addAttribute("tables", tables);
		return "list";
	}
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(Model model) {

		List<TweetsUser> list = new ArrayList<TweetsUser>();
		model.addAttribute("lists", list);
		List<String> tables = tweetsService.getTables();
		model.addAttribute("tables", tables);
		return "add";
	}

	
	@RequestMapping(value = "/delete/{tablename}/{rowid}" , method = RequestMethod.GET)
	public String deleteRow(@PathVariable("tablename") String tablename,@PathVariable("rowid") String rowid, RedirectAttributes redirectAttributes) {

		tweetsService.deleteTable(tablename, rowid);
		redirectAttributes.addFlashAttribute("tablename", tablename);
		return "redirect:/list/"+tablename;
	}
	
	
	
	
//	@GetMapping({"/", "/hello"})
//	public String  hello(@RequestParam(value = "name", defaultValue ="kiran",
//		required=true) String name, Model model) {
//		model.addAttribute("name",name);
//		return "hello";
//	}

}

