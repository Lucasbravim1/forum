package com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloWordController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "That's it !";
	}
}
