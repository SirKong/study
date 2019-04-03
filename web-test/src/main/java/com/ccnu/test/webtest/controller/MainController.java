package com.ccnu.test.webtest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gongyb08837 on 2016/1/24.
 */
@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/main.do")
    public ModelAndView doLogin(HttpServletRequest request) {
        log.debug("进入到LoginController.doLogin方法");
        return new ModelAndView("main");
    }

}
