package com.codechallenges;

import com.codechallenges.controller.PresentController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by colin.mills on 6/3/2016.
 */

public class CodeChallenge6WishlistControllerTests extends AbstractRepositoryIT{

    private MockMvc mockMvc;

    @Autowired
    PresentController specimen;

    @Autowired
    PresentServiceImpl presentService;

    @Mock
    PresentJpaRepository presentJpaRepository;

    @Mock
    WishItemJpaRepository wishItemJpaRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        presentService = new PresentServiceImpl(presentJpaRepository, wishItemJpaRepository);

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
        int id = 0;

        when(presentJpaRepository.findOne(id)).thenReturn(getPresentsGoodData().get(id));

        assertEquals(getPresentsGoodData().get(id), presentService.getPresentForId(id));

    }

    /**
     *
     * @return
     *
     * Retrieve the list of wishlist items currently in memory.
     */
    @Test
    public void getWishlist(){

        List<WishItem> wishItems = getWishlistGoodData();

        when(wishItemJpaRepository.findAll()).thenReturn(wishItems);

        assertEquals(wishItems, presentService.getWishlist());

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

        when(wishItemJpaRepository.save(wishlist)).thenReturn(wishlist);

        wishlist.add(wishItem);

        presentService.addWishItems(wishlist);

        verify(wishItemJpaRepository, times(1)).save(wishlist);

    }

    /**
     *
     * @return
     *
     * Post a new wishlist
     */
    @Test
    public void testUpdateWishItemWithGoodData(){

        int id = 2;
        WishItem wishItem = new WishItem().setGiver("Colin").setId(id);

        when(wishItemJpaRepository.findOne(id)).thenReturn(getWishlistGoodData().get(id));

        presentService.updateWishlistForId(wishItem, id);

        assertEquals("Colin", presentService.getWishlistItemForId(id).getGiver());

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
        int id = 3;

        WishItem wishItem = new WishItem().setClatters("a bit").setGiver("Colin").setId(id).setName("N64").setSize("small").setWeight("light");

        when(wishItemJpaRepository.save(wishItem)).thenReturn(wishItem);

        presentService.replaceWishItem(wishItem);

        verify(wishItemJpaRepository, times(1)).save(wishItem);
    }

    /**
     *
     * Given a present ID, delete the matching present.
     *
     */
    @Test
    public void deleteWishItem(){
        int id = 0;

        presentService.deleteWishlistItemForId(id);

        verify(wishItemJpaRepository, times(1)).delete(id);
    }
}
