package com.codechallenges;

import com.codechallenges.controller.PresentController;
import com.codechallenges.controller.WishlistController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests WishlistController endpoints given null data, bad data, and properly formatted data.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = WishlistController.class)
public class CodeChallenge6PresentControllerTests {

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
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
    @Test
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    @Test
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Present getPresentForId(@RequestParam int id){
        return presentService.getPresentForId(id);
    }

    /**
     *
     * @param presents
     * @return
     *
     * Post a new list of presents
     */
    @Test
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    public void postPresents(@RequestBody ArrayList<Present> presents){

        presentService.addPresents(presents);

    }

    /**
     *
     * @param present
     * @param id
     * @return
     *
     * Post a new list of presents
     */
    @Test
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json"})
    public void updatePresent(@RequestBody Present present, @RequestParam int id){

        presentService.updatePresentForId(present, id);

    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @Test
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    public void replacePresent(Present present){
        presentService.replacePresent(present);
    }

    //add present

    /**
     *
     *
     * Given a present ID, delete the matching present.
     *
     * Returns 404 if no such present exists.
     */
    @Test
    public void deleteWishItem(){
        int id = 0;

        presentService.deleteWishlistItemForId(id);

        verify(wishItemJpaRepository, times(1)).delete(id);
    }

}
