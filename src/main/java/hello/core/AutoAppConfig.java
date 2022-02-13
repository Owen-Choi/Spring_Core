package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 콤포넌트 스캔에서 제외할 항목을 지정해주는 코드이다.
        // Configuration.class를 제외하는 이유는 AppConfig, testConfig 등에서 학습용으로 수동 등록을 진행했는데
        // 여기서 다시 자동 등록을 해주면 충돌이 나기 때문이다.
        // 일반적으로 실무에서는 Configuration을 제외하지는 않지만 학습용으로 최대한 남겨두기 위해 제외했다고 한다.
        // ComponentScan은 말 그대로 @Component가 붙어있는 클래스를 스캔해서 스프링 빈으로 등록한다.
        // @Configuration도 안에 @Component를 포함하고 있다.
        // 구현체들에게 @Component를 모두 붙여주도록 한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = Configuration.class)
)
public class AutoAppConfig {

}
