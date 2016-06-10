package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.entity.Present;
import com.codechallenges.entity.WishItem;
import com.codechallenges.service.PresentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Resource
    PresentService presentService;

    /**
     *
     * @return
     *
     * Retrieve the list of presents currently in memory.
     */
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
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json"})
    public void updatePresent(@RequestBody Present present, @RequestParam int id){

        presentService.updatePresentForId(present, id);

    }

    /**
     * Add a present item to the existing list
     * @param present
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    public void replacePresent(Present present){
        presentService.replacePresent(present);
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
    @Test
    public void deleteWishItem(@RequestParam int id){
        presentService.deleteWishlistItemForId(id);

        assertEquals(null, presentService.getWishlist());
    }

}
