package com.ccnu.test.webtest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ccnu.test.webtest.service.UserService;

/**
 * Created by gongyb08837 on 2016/1/24.
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public ModelAndView doLogin(HttpServletRequest request) {
        log.debug("进入到LoginController.doLogin方法");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.loginCheck(username, password)) {
            return new ModelAndView("redirect:main.do");
        } else {
            return new ModelAndView("error.jsp");
        }
    }
}
