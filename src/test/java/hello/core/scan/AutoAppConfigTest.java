package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // ComponentScan의 경우 스프링 빈의 기본 이름은 클래스 명을 사용하되 맨 앞 글자만 소문자로 바꿔서 사용한다.
        // 이름을 직접 지정하고 싶다면 @Component("memberServiceImpl") 과 같이 지정하면 된다.
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
