package com.wyz.controller;

import com.wyz.entity.WxUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestParam(defaultValue = "0") Integer type) {
        return "test";
    }
}
