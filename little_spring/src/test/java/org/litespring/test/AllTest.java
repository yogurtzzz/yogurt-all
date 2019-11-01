package org.litespring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.ApplicationContextTestV1;
import org.litespring.test.v1.BeanFactoryTest;
import org.litespring.test.v1.ResourceTest;
import org.litespring.test.v2.ApplicationContextTestV2;

/**
 * 将所有test类放在一起运行
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTestV1.class,
        ResourceTest.class,
        ApplicationContextTestV2.class
})
public class AllTest {
}
