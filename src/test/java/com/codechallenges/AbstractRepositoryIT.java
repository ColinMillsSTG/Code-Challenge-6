package com.codechallenges;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by colin.mills on 6/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
@SpringApplicationConfiguration(classes = CodeChallenge6Application.class)
@WebAppConfiguration
@DirtiesContext
public abstract class AbstractRepositoryIT extends AbstractTransactionalJUnit4SpringContextTests{
}
