package com.codechallenges;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by colin.mills on 6/9/2016.
 */
@TestExecutionListeners({DbUnitTestExecutionListener.class})
@SpringApplicationConfiguration(classes = CodeChallenge6Application.class)
@DirtiesContext
public abstract class AbstractRepositoryIT extends AbstractTransactionalJUnit4SpringContextTests{
}
