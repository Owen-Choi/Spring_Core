package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    // 스프링은 xml을 이용한 AppConfig 파일 작성도 지원한다.
    // 현재에는 거의 쓰지않는다고 하니 그냥 "스프링이 이렇게 유연하구나" 정도만 알고있으면 된다고 한다.
    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
