package com.lind.fast.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lind
 * @date 2022/10/8 17:08
 * @since 1.0.0
 */
@Controller
@RequestMapping("user-view")
public class ViewController {

	@GetMapping("index")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("ftl/index");
		modelAndView.addObject("error", "出错了");
		return modelAndView;
	}

}
