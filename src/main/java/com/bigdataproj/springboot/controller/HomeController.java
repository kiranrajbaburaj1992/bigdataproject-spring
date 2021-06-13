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

import com.bigdataproj.springboot.service.TableService;

@Controller
public class HomeController {

	@Autowired
	TableService tableService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Model model) {

		List<String> tables = tableService.getTables();
		model.addAttribute("tables",tables);
		return "home";
	}


	@RequestMapping(value = "/list/{tablename}" , method = RequestMethod.GET)
	public String list(Model model,@PathVariable("tablename") String tablename) {

		model.addAttribute("lists", tableService.getTableRows(tablename));
		List<String> tables = tableService.getTables();
		model.addAttribute("tablename", tablename);
		model.addAttribute("tables", tables);
		return "list";
	}


	@RequestMapping(value = { "/put/{tablename}" }, method = RequestMethod.GET)
	public String put(Model model,@PathVariable("tablename") String tablename) {

		List<String> tables = tableService.getTables();
		model.addAttribute("tables", tables);
		return "put";
	}
	
	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public String create(Model model) {

		List<String> tables = tableService.getTables();
		model.addAttribute("tables", tables);
		return "create";
	}


	@RequestMapping(value = "/delete/{tablename}/{rowid}" , method = RequestMethod.GET)
	public String deleteRow(@PathVariable("tablename") String tablename,@PathVariable("rowid") String rowid, RedirectAttributes redirectAttributes) {

		tableService.deleteTable(tablename, rowid);
		redirectAttributes.addFlashAttribute("tablename", tablename);
		return "redirect:/list/"+tablename;
	}
	
	
	@RequestMapping(value = "/get/{tablename}/{rowid}" , method = RequestMethod.GET)
	public String getRow(Model model,@PathVariable("tablename") String tablename, @PathVariable("rowid") String rowid) {
		model.addAttribute("map", tableService.getTableRow(tablename,rowid));
		List<String> tables = tableService.getTables();
		model.addAttribute("tablename", tablename);
		model.addAttribute("rowid", rowid);
		model.addAttribute("tables", tables);
		return "list";
	}

}

