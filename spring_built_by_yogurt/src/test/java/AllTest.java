import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//使用Suite来一次性执行所有的Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTest.class,
        ResourceTest.class,
        SetterInjectionTest.class
})
public class AllTest {
}
