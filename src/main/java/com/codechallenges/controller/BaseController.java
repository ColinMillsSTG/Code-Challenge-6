package com.codechallenges.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by colin.mills on 6/17/2016.
 */
@RestController
public class BaseController {

    @RequestMapping("/")
    public String getIndex(){

        return "index";

    }

}
