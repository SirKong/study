package com.ccnu.test.springboot.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccnu.test.springboot.constant.OpenAPIResult;
import com.google.common.collect.Lists;

@RestController("/")
public class HelloController {

    private static final Integer MAX = 93;

    private static final List<Integer> DATA = Lists.newArrayList();

    static {
        for (int i = 0; i < MAX; i++) {
            DATA.add(i + 1);
        }
    }


    @GetMapping(params = {"Action=ListByPage"})
    public OpenAPIResult listByPage(@RequestParam("Offset") Integer offset, @RequestParam("Limit") Integer limit) {
        if (offset > MAX) {
            return OpenAPIResult.buildSuccess(Lists.newArrayList(), MAX);
        }

        limit = limit > MAX ? MAX : limit;

        return OpenAPIResult.buildSuccess(DATA.subList(offset, limit), MAX);
    }
}