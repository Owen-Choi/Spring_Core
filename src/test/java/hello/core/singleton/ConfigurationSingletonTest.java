package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 원래는 아래와 같이 구체타입으로 꺼내고 하면 안좋지만 테스트를 위해 구체타입을 바로 쓰도록 하겠다.
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        // MemberRepository 원본.
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        MemberRepository MembermemberRepository = memberService.getMemberRepository();
        MemberRepository OrdermemberRepository = orderService.getMemberRepository();

        System.out.println("MembermemberRepository = " + MembermemberRepository);
        System.out.println("OrdermemberRepository = " + OrdermemberRepository);
        // MemberRepository 원본.
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(MembermemberRepository).isSameAs(OrdermemberRepository);
    }
}
