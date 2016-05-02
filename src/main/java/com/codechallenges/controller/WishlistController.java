package com.codechallenges.controller;

import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.service.PresentService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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

    @RequestMapping(value = "getMatches", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getMatches(){
        return presentService.guessPresents();
    }

    /**
     *
     * @param wishlist
     * @return
     *
     * Post a new wishlist
     */
    @RequestMapping(value = "wishlist", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ArrayList<String> postWishlist(@RequestBody ArrayList<Map<String, String>> wishlist){

        return presentService.guessPresents(wishlist);

        //presentService.setWishlist(wishlist);

    }

    /**
     *
     * @param presents
     * @return
     *
     * Post a new list of presents
     */
    @RequestMapping(value = "presents", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public void postPresents(@RequestBody ArrayList<Map<String,String>> presents){

        presentService.setPresents(presents);

    }

    /**
     *
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
    @RequestMapping(value = "presents", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Map<String,String>> getPresents(){
        return presentService.getPresents();
    }

    /**
     *
     * @param id
     * @return
     *
     * Given a present ID, find a matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "presents/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getPresentForId(@RequestParam int id){
        return presentService.getPresentForId(id);
    }

    /**
     *
     * @param id
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "presents/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deletePresent(@RequestParam int id){
            presentService.deletePresentForId(id);
    }

}
