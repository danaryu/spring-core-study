package hello.core.singleton;

import hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A : A사용자가 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);
        //Thread B : B사용자가 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAprice);

        //assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}
