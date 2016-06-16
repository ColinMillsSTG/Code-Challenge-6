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

    /**
     * Returns the wishlist item with the given matching ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public @ResponseBody WishItem getWishItem(@RequestParam int id){
        return presentService.getWishlistItemForId(id);
    }

    /**
     *
     * @return
     *
     * Retrieve the list of wishlist items currently in memory.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<WishItem> getWishlist(){
        return presentService.getWishlist();
    }

    /**
     *
     * @param wishlist
     * @return
     *
     * Post a new wishlist
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    public void addWishItems(@RequestBody ArrayList<WishItem> wishlist){

        presentService.addWishItems(wishlist);

    }

    /**
     *
     * @param wishlist
     * @param id
     * @return
     *
     * Post a new wishlist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json"})
    public void updateWishItem(@RequestBody WishItem wishlist, @RequestParam int id){

        presentService.updateWishlistForId(wishlist, id);

    }

    /**
     * Add a wishlist item to the existing list
     * @param wishItem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    public void addWishListItem(WishItem wishItem){
        presentService.replaceWishItem(wishItem);
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
