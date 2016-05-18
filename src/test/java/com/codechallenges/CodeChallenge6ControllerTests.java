package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.service.PresentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests WishlistController endpoints given null data, bad data, and properly formatted data.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WishlistController.class)
@WebAppConfiguration
public class CodeChallenge6ControllerTests {

    private MockMvc mockMvc;

    @Autowired
    PresentService presentService;

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
}
