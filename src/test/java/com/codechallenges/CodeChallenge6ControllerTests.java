package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests WishlistController endpoints given null data, bad data, and properly formatted data.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WishlistController.class)
@WebAppConfiguration
public class CodeChallenge6ControllerTests {


    @Test
    public void testShakePresentsNull(){

    }

    @Test
    public void testShakePresentsBadData(){

    }

    @Test
    public void testShakePresentsGoodData(){

    }
}
