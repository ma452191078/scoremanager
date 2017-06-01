package com.majy.scoremanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by majingyuan on 2017/5/29.
 *
 */
@Controller
public class indexController {
    @RequestMapping("/")
    public String helloHtml(Map<String,Object> map){

        return"/index";
    }
}
