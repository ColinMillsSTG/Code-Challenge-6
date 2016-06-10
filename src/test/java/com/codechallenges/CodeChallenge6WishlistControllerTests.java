package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.service.PresentService;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by colin.mills on 6/3/2016.
 */

@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
@IntegrationTest
public class CodeChallenge6WishlistControllerTests {

    PresentServiceImpl presentService;

    @PostConstruct
    public void setup(){
        presentService = new PresentServiceImpl();

        presentService.addWishItems(getWishlistGoodData());
        presentService.addPresents(getPresentsGoodData());

    }

    /**
     *
     * Setting up data
     *
     */

    public ArrayList<WishItem> getWishlistGoodData(){

        ArrayList<WishItem> wishlist = new ArrayList<>();

        wishlist.add(new WishItem().setId(0).setName("Mini Puzzle").setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        wishlist.add(new WishItem().setId(1).setName("Toy Car").setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));
        wishlist.add(new WishItem().setId(2).setName("Card Game").setSize("small").setClatters("no").setWeight("light").setGiver("Louie"));

        return wishlist;

    }

    public ArrayList<Present> getPresentsGoodData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setId(0).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        presents.add(new Present().setId(1).setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));

        return presents;

    }

    /**
     *
     * Begin tests
     *
     */

    /**
     * Returns the wishlist item with the given matching ID
     * @return
     */
    @Test
    public void testGetWishItem(){



    }

    /**
     *
     * @return
     *
     * Retrieve the list of wishlist items currently in memory.
     */
    @Test
    public void getWishlist(){

        assertEquals(getWishlistGoodData(),presentService.getWishlist());

    }

    /**
     *
     * @return
     *
     * Post a new wishlist
     */
    @Test
    public void testAddWishItems(){

        ArrayList<WishItem> wishlist = new ArrayList<>();
        WishItem wishItem = new WishItem().setClatters("a bit").setGiver("Colin").setId(4).setName("N64").setSize("small").setWeight("light");

        wishlist.add(wishItem);

        presentService.addWishItems(wishlist);

        assertEquals(wishItem, presentService.getWishlistItemForId(4));

    }

    /**
     *
     * @return
     *
     * Post a new wishlist
     */
    @Test
    public void testUpdateWishItemWithGoodData(){

        WishItem wishItem = new WishItem().setGiver("Colin").setId(3);

        presentService.updateWishlistForId(wishItem, 3);

        assertEquals("Colin", presentService.getWishlistItemForId(3).getGiver());

    }

    @Test
    public void testUpdateWishItemFailsWhenUpdatingNonextantItem(){

        WishItem wishItem = new WishItem().setGiver("Colin").setId(3);

        try{
            presentService.updateWishlistForId(wishItem, 1);

        }catch(Exception e){
            //This is expected
        }

    }

    /**
     * Add a wishlist item to the existing list
     */
    @Test
    public void testAddWishListItem(){
        WishItem wishItem = new WishItem().setClatters("a bit").setGiver("Colin").setId(4).setName("N64").setSize("small").setWeight("light");

        presentService.replaceWishItem(wishItem);

        assertEquals(wishItem, presentService.getWishlistItemForId(4));
    }

    /**
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @Test
    public void deleteWishItem(){
        int id = 0;

        presentService.addWishItems(getWishlistGoodData());

        presentService.deleteWishlistItemForId(id);

        assertEquals(null, presentService.getWishlist());
    }
}
