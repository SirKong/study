package com.ccnu.test.hsf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ccnu.test.hsf.service.HelloWorldService;

@Controller
public class HelloWorldController {

	@Resource
	private HelloWorldService helloWorldServiceCustomer;

	@RequestMapping("/hello.html")
	public ModelAndView helloWorld() {

		String message = helloWorldServiceCustomer.sayHello("臣天");
		System.out.println(message);
		return new ModelAndView("hello", "message", message);
	}
}
