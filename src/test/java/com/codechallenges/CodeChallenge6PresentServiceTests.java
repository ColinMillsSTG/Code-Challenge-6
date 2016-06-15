package com.codechallenges;

import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.exceptions.ResourceNotFoundException;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentService;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests to check PresentServiceImpl functionality.
 */

public class CodeChallenge6PresentServiceTests extends AbstractRepositoryIT{

    PresentServiceImpl specimen;

    @Mock
    PresentJpaRepository presentJpaRepository;

    @Mock
    WishItemJpaRepository wishItemJpaRepository;

    /**
     *
     * Setting up data
     *
     */

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        specimen = new PresentServiceImpl(presentJpaRepository, wishItemJpaRepository);

    }

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
        specimen.guessPresents();
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

        specimen.addWishItems(wishlist);
        specimen.addPresents(presents);

        assertEquals(expected, specimen.guessPresents());
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

        specimen.addWishItems(wishlist);
        specimen.addPresents(presents);

        assertEquals(expected, specimen.guessPresents());
    }

    @Test
    public void testSetAndGetWishlist(){

        ArrayList<WishItem> wishItems = getWishlistGoodData();

        specimen.addWishItems(wishItems);

        assertEquals(wishItems, specimen.getWishlist());

    }

    @Test
    public void testSetAndGetPresents(){

        ArrayList<Present> presents = getPresentsGoodData();

        when(presentJpaRepository.save(any())).thenReturn(null);

        specimen.addPresents(presents);

        verify(presentJpaRepository, times(1)).save(presents);

    }

    @Test
    public void testAddWishlistItem(){

        ArrayList<WishItem> wishItems = getWishlistGoodData();

        WishItem addedItem = new WishItem().setName("Laptop").setClatters("no").setSize("medium").setWeight("light");

        specimen.addWishItems(wishItems);
        specimen.replaceWishItem(addedItem);

        wishItems.add(addedItem);

        when(specimen.getWishlist()).thenReturn(wishItems);

        assertEquals(wishItems, specimen.getWishlist());

    }

    @Test
    public void testAddPresent(){

        ArrayList<Present> presents = getPresentsGoodData();

        Present addedItem = new Present().setClatters("no").setSize("medium").setWeight("light");

        specimen.addPresents(presents);
        specimen.replacePresent(addedItem);

        presents.add(addedItem);

        assertEquals(presents, specimen.getPresents());

    }

    @Test
    public void testGetWishlistItemForId(){

        specimen.addWishItems(getWishlistGoodData());

        WishItem wishItem = new WishItem().setId(0).setName("Mini Puzzle").setSize("small").setClatters("yes").setWeight("light").setGiver("Frank");

        assertEquals(wishItem, specimen.getWishlistItemForId(0));

    }

    @Test
    public void testGetPresentForId(){

        specimen.addPresents(getPresentsGoodData());

        Present present = new Present().setId(0).setSize("small").setClatters("yes").setWeight("light").setGiver("Frank");

        assertEquals(present, specimen.getPresentForId(0));

    }

    @Test (expected = ResourceNotFoundException.class)
    public void testDeleteWishlistItem(){

        ArrayList<String> expected = new ArrayList<>();

        specimen.addWishItems(getWishlistGoodData());

        specimen.deleteWishlistItemForId(0);

        specimen.getWishlistItemForId(0);

    }

    @Test (expected = ResourceNotFoundException.class)
    public void testDeletePresentForId(){

        Present expected = new Present();

        specimen.addPresents(getPresentsGoodData());

        specimen.deletePresentForId(0);

        assertEquals(expected, specimen.getPresentForId(0));

    }

    @Test
    public void testClearWishlist(){

        specimen.clearWishlist();

        verify(wishItemJpaRepository, times(1)).deleteAll();

    }

    @Test
    public void testClearPresents(){

        specimen.clearPresents();

        verify(presentJpaRepository, times(1)).deleteAll();

    }

}
