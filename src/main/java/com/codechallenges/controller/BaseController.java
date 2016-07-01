package com.codechallenges.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by colin.mills on 6/17/2016.
 */
@Controller
@RequestMapping("/")
public class BaseController {
/*
    @RequestMapping(method = GET)
    public String index(){

        return "index";

    }*/

    /*
    Create
     */
  /*  @RequestMapping(value = "createWishlist", method = GET)
    public String createWishlist(){
        return "wishlist/createWishlist";
    }*/

    @RequestMapping(value = "public/presents/add", method = GET)
    public String createPresents(){
        return "addPresents";
    }

    /*
    Read
     */
    @RequestMapping(value = "readWishlist", method = GET)
    public String readWishlist(){
        return "wishlist/readWishlist";
    }

    @RequestMapping(value = "public/presents/read", method = GET)
    public String readPresentList(){
        return "showPresents";
    }

    /*
    Update
     */
    @RequestMapping(value = "updateWishlist", method = GET)
    public String updateWishlist(){
        return "wishlist/editWishlist";
    }

    @RequestMapping(value = "public/presents/update", method = GET)
    public String updatePresents(){
        return "updatePresents";
    }

    /*
    Delete
     */
    @RequestMapping(value = "deleteWishlist", method = GET)
    public String deleteWishlist(){
        return "clearWishlist";
    }

    @RequestMapping(value = "public/presents/delete", method = GET)
    public String deletePresents(){
        return "clearPresents";
    }

}
