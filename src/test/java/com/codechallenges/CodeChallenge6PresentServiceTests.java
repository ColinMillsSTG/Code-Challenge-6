package com.codechallenges;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests to check PresentServiceImpl functionality.
 */

@RunWith(MockitoJUnitRunner.class)
//@SpringApplicationConfiguration(classes = PresentService.class)
//@WebAppConfiguration
public class CodeChallenge6PresentServiceTests extends AbstractRepositoryIT{

    @Mock
    PresentService presentService;

    @Autowired
    PresentJpaRepository presentJpaRepository;

    @Autowired
    WishItemJpaRepository wishItemJpaRepository;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
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

    public ArrayList<WishItem> getWishlistBadData(){

        ArrayList<WishItem> wishlist = new ArrayList<>();

        wishlist.add(new WishItem().setName("blah").setSize("blah").setClatters("blah").setWeight("blah"));
        wishlist.add(new WishItem().setName("Helium").setSize("2").setClatters("0").setWeight(".000002"));
        wishlist.add(new WishItem().setName("janky").setSize("tanky").setClatters("ganky").setWeight("healbot"));

        return wishlist;

    }

    public ArrayList<Present> getPresentsGoodData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setId(0).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank"));
        presents.add(new Present().setId(1).setSize("medium").setClatters("a bit").setWeight("medium").setGiver("James"));

        return presents;

    }

    public ArrayList<Present> getPresentsBadData(){

        ArrayList<Present> presents = new ArrayList<>();

        presents.add(new Present().setSize("foo").setClatters("bar").setWeight("baz"));
        presents.add(new Present().setSize("huge").setClatters("I think it's broken").setWeight("weightless"));

        return presents;

    }

    /**
     *
     * Begin tests
     *
     */

    /**
     * Test for guessPresents() with null data
     * We're expecting this to error out
     */
    @Test
    public void testGuessPresentsNullData(){
        presentService.guessPresents();
    }

    /**
     * Test for guessPresents() with bad data
     * Should not return any matches
     */
    @Test
    public void testGuessPresentsBadData(){
        ArrayList<WishItem> wishlist = getWishlistGoodData();
        ArrayList<Present> presents = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();

        presentService.addWishItems(wishlist);
        presentService.addPresents(presents);

        assertEquals(expected,presentService.guessPresents());
    }

    /**
     * This test should return the expected presents.
     */
    @Test
    public void testGuessPresentsGoodData(){
        //set data that will return data
        ArrayList<WishItem> wishlist = getWishlistGoodData();
        ArrayList<Present> presents = getPresentsGoodData();
        ArrayList<String> expected = new ArrayList<>();

        expected.add("Mini Puzzle");
        expected.add("Toy Car");

        presentService.addWishItems(wishlist);
        presentService.addPresents(presents);

        assertEquals(expected, presentService.guessPresents());
    }

    @Test
    public void testSetAndGetWishlist(){

        ArrayList<WishItem> wishItems = getWishlistGoodData();

        presentService.addWishItems(wishItems);

        assertEquals(wishItems,presentService.getWishlist());

    }

    @Test
    public void testSetAndGetPresents(){

        ArrayList<Present> presents = getPresentsGoodData();

        presentService.addPresents(presents);

        assertEquals(presents,presentService.getPresents());

    }

    @Test
    public void testAddWishlistItem(){

        ArrayList<WishItem> wishItems = getWishlistGoodData();

        WishItem addedItem = new WishItem().setName("Laptop").setClatters("no").setSize("medium").setWeight("light");

        presentService.addWishItems(wishItems);
        presentService.replaceWishItem(addedItem);

        wishItems.add(addedItem);

        when(presentService.getWishlist()).thenReturn(wishItems);

        assertEquals(wishItems,presentService.getWishlist());

    }

    @Test
    public void testAddPresent(){

        ArrayList<Present> presents = getPresentsGoodData();

        Present addedItem = new Present().setClatters("no").setSize("medium").setWeight("light");

        presentService.addPresents(presents);
        presentService.replacePresent(addedItem);

        presents.add(addedItem);

        assertEquals(presents,presentService.getPresents());

    }

    @Test
    public void testGetWishlistItemForId(){

        presentService.addWishItems(getWishlistGoodData());

        WishItem wishItem = new WishItem().setId(0).setName("Mini Puzzle").setSize("small").setClatters("yes").setWeight("light").setGiver("Frank");

        assertEquals(wishItem, presentService.getWishlistItemForId(0));

    }

    @Test
    public void testGetPresentForId(){

        presentService.addPresents(getPresentsGoodData());

        Present present = new Present().setId(0).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank");

        assertEquals(present, presentService.getPresentForId(0));

    }

    @Test (expected = ResourceNotFoundException.class)
    public void testDeleteWishlistItem(){

        ArrayList<String> expected = new ArrayList<>();

        presentService.addWishItems(getWishlistGoodData());

        presentService.deleteWishlistItemForId(0);

        presentService.getWishlistItemForId(0);

    }

    @Test (expected = ResourceNotFoundException.class)
    public void testDeletePresentForId(){

        Present expected = new Present();

        presentService.addPresents(getPresentsGoodData());

        presentService.deletePresentForId(0);

        assertEquals(expected, presentService.getPresentForId(0));

    }

    @Test
    public void testClearWishlist(){

        ArrayList<WishItem> expected = new ArrayList<>();

        presentService.addWishItems(getWishlistGoodData());

        presentService.clearWishlist();

        assertEquals(expected, presentService.getWishlist());

    }

    @Test
    public void testClearPresents(){

        ArrayList<Present> expected = new ArrayList<>();

        presentService.addPresents(getPresentsGoodData());

        presentService.clearPresents();

        assertEquals(expected, presentService.getPresents());

    }

}
