package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.service.PresentService;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests to check PresentServiceImpl functionality.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PresentServiceImpl.class)
@WebAppConfiguration
public class CodeChallenge6PresentServiceTests {

    @Autowired
    PresentService presentService;

    /**
     * Test for guessPresents() with null data
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testGuessPresentsNullData(){
        presentService.guessPresents();
    }

    /**
     * Test for guessPresents() with bad data
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testGuessPresentsBadData(){
        //set data that won't return any matches
        ArrayList<Map<String,String>> wishlist = new ArrayList<>();
        ArrayList<Map<String,String>> presents = new ArrayList<>();

        HashMap<String,String> wishlistItem = new HashMap<>();
        HashMap<String,String> wishlistItem2 = new HashMap<>();
        HashMap<String,String> wishlistItem3 = new HashMap<>();

        HashMap<String,String> presentsItem = new HashMap<>();
        HashMap<String,String> presentsItem2 = new HashMap<>();

        wishlistItem.put("name","Mini Puzzle");
        wishlistItem.put("size","small");
        wishlistItem.put("clatters","yes");
        wishlistItem.put("weight","light");

        wishlistItem2.put("name","Toy Car");
        wishlistItem2.put("size","medium");
        wishlistItem2.put("clatters","a bit");
        wishlistItem2.put("weight","medium");

        wishlistItem3.put("name","Card Game");
        wishlistItem3.put("size","small");
        wishlistItem3.put("clatters","no");
        wishlistItem3.put("weight","light");

        presentsItem.put("foo", "medium");
        presentsItem.put("bar","a bit");
        presentsItem.put("baz","medium");

        presentsItem2.put("foo","small");
        presentsItem2.put("bar","yes");
        presentsItem2.put("baz","light");

        wishlist.add(wishlistItem);
        wishlist.add(wishlistItem2);
        wishlist.add(wishlistItem3);

        presents.add(presentsItem);
        presents.add(presentsItem2);

        assertNull(presentService.guessPresents());
    }

    public void testGuessPresentsGoodData(){
        //set data that will return data
        ArrayList<Map<String,String>> wishlist = new ArrayList<>();
        ArrayList<Map<String,String>> presents = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();

        HashMap<String,String> wishlistItem = new HashMap<>();
        HashMap<String,String> wishlistItem2 = new HashMap<>();
        HashMap<String,String> wishlistItem3 = new HashMap<>();

        HashMap<String,String> presentsItem = new HashMap<>();
        HashMap<String,String> presentsItem2 = new HashMap<>();

        wishlistItem.put("name","Mini Puzzle");
        wishlistItem.put("size","small");
        wishlistItem.put("clatters","yes");
        wishlistItem.put("weight","light");

        wishlistItem2.put("name","Toy Car");
        wishlistItem2.put("size","medium");
        wishlistItem2.put("clatters","a bit");
        wishlistItem2.put("weight","medium");

        wishlistItem3.put("name","Card Game");
        wishlistItem3.put("size","small");
        wishlistItem3.put("clatters","no");
        wishlistItem3.put("weight","light");

        presentsItem.put("size", "medium");
        presentsItem.put("clatters","a bit");
        presentsItem.put("weight","medium");

        presentsItem2.put("size","small");
        presentsItem2.put("clatters","yes");
        presentsItem2.put("weight","light");

        wishlist.add(wishlistItem);
        wishlist.add(wishlistItem2);
        wishlist.add(wishlistItem3);

        presents.add(presentsItem);
        presents.add(presentsItem2);

        expected.add("Toy Car");
        expected.add("Mini Puzzle");

        assertEquals(expected, presentService.guessPresents());
    }

    /* @Todo: write these tests
    void setWishlist(ArrayList<Map<String,String>> wishlist);
    void setPresents(ArrayList<Map<String,String>> presents);

    ArrayList<Map<String,String>> getPresents();

    Map<String,String> getPresentForId(int id) throws ResourceNotFoundException;

    void deletePresentForId(int id) throws ResourceNotFoundException;

    void clearPresents();
    void clearWishlist();
    */
}
