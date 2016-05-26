package com.codechallenges.controller;

import com.codechallenges.entity.WishItem;
import com.codechallenges.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Contains end-points for wish list guessing service.
 */

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    /**
     * @Todo: Change to wishlistService
     */
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
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<WishItem> getWishlist(){
        return presentService.getWishlist();
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WishItem getWishItemForId(@RequestParam int id){
        return presentService.getWishlistItemForId(id);
    }

    /**
     *
     * @param wishlist
     * @return
     *
     * Post a new wishlist
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = {"application/json"})
    public void postWishlist(@RequestBody ArrayList<WishItem> wishlist){

        presentService.setWishlist(wishlist);

    }

    /**
     * Add a wishlist item to the existing list
     * @param wishItem
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = {"application/json"})
    public void addWishListItem(WishItem wishItem){
        presentService.addWishlistItem(wishItem);
    }

    /**
     *
     * @param id
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteWishItem(@RequestParam int id){
            presentService.deleteWishlistItemForId(id);
    }

}
