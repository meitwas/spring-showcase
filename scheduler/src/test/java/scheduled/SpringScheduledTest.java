package scheduled;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringScheduledTest {

    private static AbstractApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("META-INF/application-config.xml");
//        context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-db.xml");
    }

    @Test
    @DisplayName("Test DB")
    void test1() throws InterruptedException {
        Thread.sleep(60000);
    }
}
