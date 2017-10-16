package tx;

import com.ssn.spring.tx.service.CountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTxTest {

    private static AbstractApplicationContext context;

    @BeforeAll
    public static void init() {
        context = new ClassPathXmlApplicationContext("META-INF/application-config.xml");
//        context = new ClassPathXmlApplicationContext("META-INF/spring/application-config-db.xml");
    }

    @Test
    @DisplayName("Test DB")
    void test1() {
        CountService contactService = context.getBean("contactService", CountService.class);
        contactService.countAll();
    }

    @Test
    @DisplayName("Test Propagation")
    void test2() {
        CountService contactService = context.getBean("contactServiceTransactional", CountService.class);
        contactService.countAll();
    }
}
