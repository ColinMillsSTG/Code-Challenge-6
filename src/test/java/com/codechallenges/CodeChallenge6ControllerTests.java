package com.codechallenges;

import com.codechallenges.controller.WishlistController;
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

import javax.annotation.Resource;
import java.util.ArrayList;

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
public class CodeChallenge6ControllerTests {

    private MockMvc mockMvc;

    @Resource
    PresentService presentService;

    /**
     * Get Matches tests
     */



    /**
     * Post wishlist tests
     */

    @Test
    public void testPostWishlistNullData() throws Exception{
        ArrayList<WishItem> wishlist = new ArrayList<>();

        mockMvc.perform(post("/wislist")
                .contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Get wishlist tests
     */

    @Test
    public void testShakePresentsNullData() throws Exception{
        mockMvc.perform(get("/wishlist"));

    }

    @Test
    public void testShakePresentsBadData() throws Exception{
        //seed with bad data
        mockMvc.perform(get("/wishlist"));

    }

    @Test
    public void testShakePresentsGoodData() throws Exception{
        //seed with good data
        mockMvc.perform(get("/wishlist"));

        //Expect

    }

    /**
     * Methods to test
     *
     */
}
