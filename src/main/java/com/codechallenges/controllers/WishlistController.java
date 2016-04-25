package com.codechallenges.controllers;

import com.codechallenges.services.PresentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Contains end-points for wish list guessing services.
 */

@Controller
@RequestMapping("/")
public class WishlistController {

    PresentService presentService;

    @RequestMapping("wishlist")
    public ArrayList<String> shakePresents(@RequestParam(value="wishlist") String wishlist, @RequestParam(value="presents") String presents){

        return presentService.guessPresents(wishlist, presents);
    }


}
