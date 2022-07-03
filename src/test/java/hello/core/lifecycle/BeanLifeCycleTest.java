package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ConfigurableApplicationContext는 AnnotationConfigApplicationContext의 하위 클래스
        // ConfigurableApplicationContext를 사용하는 이유는 close 기능을 제공하기 때문이라고 한다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // 인터페이스 구현으로 콜백 메서드를 정의하는건 구식이다. 이렇게 bean 애노테이션에 초기화, 소멸 콜백을 지정해줄 수 있다.
        // 하지만 이 방법 또한 deprecated하다. 최신 스프링이  권장하는 방법은 @PostConstruct, @PreDestroy 애노테이션이다.
        @Bean/*(initMethod = "Init", destroyMethod = "close")*/
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
