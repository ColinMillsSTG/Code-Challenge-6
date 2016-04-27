package com.codechallenges;

import com.codechallenges.controller.WishlistController;
import com.codechallenges.service.PresentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by colin.mills on 4/27/2016.
 *
 * Tests to check PresentServiceImpl functionality.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PresentServiceImpl.class)
@WebAppConfiguration
public class CodeChallenge6PresentServiceTests {

    @Test
    public void guessPresentsTestNullData(){

    }

    @Test
    public void guessPresentsBadData(){

    }

    @Test
    public void guessPresentsNoMatches(){

    }

    @Test
    public void guessPresentsWithMatches(){

    }
}
