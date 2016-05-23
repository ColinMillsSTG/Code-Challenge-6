package com.codechallenges.controller;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.service.PresentService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Contains end-points for wish list guessing service.
 */

@RestController
@RequestMapping("/")
public class WishlistController {

    @Autowired
    PresentService presentService;

    @RequestMapping(value = "getMatches", method = RequestMethod.GET)
    public List<String> getMatches(){
        return presentService.guessPresents();
    }

    /**
     *
     * @return
     *
     * Retrieve the list of wishlist items currently in memory.
     */
    @RequestMapping(value = "wishlist", method = RequestMethod.GET)
    public List<WishItem> getWishlist(){
        return presentService.getWishlist();
    }

    /**
     *
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
    @RequestMapping(value = "presents", method = RequestMethod.GET)
    public List<Present> getPresents(){
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
    @RequestMapping(value = "wishlist/{id}", method = RequestMethod.GET)
    public WishItem getWishItemForId(@RequestParam int id){
        return presentService.getWishlistItemForId(id);
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
    public Present getPresentForId(@RequestParam int id){
        return presentService.getPresentForId(id);
    }

    /**
     *
     * @param wishlist
     * @return
     *
     * Post a new wishlist
     */
    @RequestMapping(value = "wishlist/new", method = RequestMethod.POST, consumes = {"application/json"})
    public void postWishlist(@RequestBody ArrayList<WishItem> wishlist){

        presentService.setWishlist(wishlist);

    }

    /**
     *
     * @param presents
     * @return
     *
     * Post a new list of presents
     */
    @RequestMapping(value = "presents/new", method = RequestMethod.POST, consumes = {"application/json"})
    public void postPresents(@RequestBody ArrayList<Present> presents){

        presentService.setPresents(presents);

    }

    /**
     * Add a wishlist item to the existing list
     * @param wishItem
     */
    @RequestMapping(value = "wishlist", method = RequestMethod.PUT, consumes = {"application/json"})
    public void addWishListItem(WishItem wishItem){
        presentService.addWishlistItem(wishItem);
    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @RequestMapping(value = "presents", method = RequestMethod.PUT, consumes = {"application/json"})
    public void addPresent(Present present){
        presentService.addPresent(present);
    }

    //add present

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

    /**
     *
     * @param id
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "wishlist/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteWishItem(@RequestParam int id){
            presentService.deleteWishlistItemForId(id);
    }

}
