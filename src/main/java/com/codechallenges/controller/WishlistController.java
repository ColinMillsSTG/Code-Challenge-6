package com.codechallenges.controller;

import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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
    public ArrayList<String> shakePresents(@RequestParam(value="wishlist") String wishlist, @RequestParam(value="presents") String presents){

        return presentService.guessPresents(wishlist, presents);
    }


}
