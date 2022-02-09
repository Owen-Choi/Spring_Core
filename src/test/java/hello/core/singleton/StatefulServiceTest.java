package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
    @Test
    void StatefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        //statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        //statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        // int price = statefulService1.getPrice();
        // 결과는 20000원으로 우리가 원치않는 결과가 나오게 된다.
        // 이유는 알다시피 싱글톤에 의해 같은 인스턴스를 사용하기 때문.
        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        //문제를 해결하기 위해서는 수정된 StatefulService 클래스와 함께 다음과 같이 코드를 짜도록 한다.
        // 정적변수를 지역변수화 시켜줌.
        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }
    // @Configuration 안필요하나?
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
