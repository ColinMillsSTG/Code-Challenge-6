package com.codechallenges.controller;

import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Contains end-points for wish list guessing service.
 */

@Controller
@RequestMapping("/")
public class WishlistController {

    @Autowired
    PresentService presentService;

    @RequestMapping("wishlist")
    @ResponseBody
    public ArrayList<String> shakePresents(@RequestParam(value="wishlist") ArrayList<Map<String, String>> wishlist, @RequestParam(value="presents") ArrayList<Map<String, String>> presents){

        return presentService.guessPresents(wishlist, presents);
    }


}
