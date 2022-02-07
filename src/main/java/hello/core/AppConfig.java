package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring에서 AppConfig을 사용하기 위해서 해당 어노테이션이 필요하다.
@Configuration
public class AppConfig {

    // MemberServiceImpl을 외부(AppConfig)에서 생성을 해주고, 원래 MemberServiceImpl 내부에서 생성해줬던
    // MemoryMemberRepository도 여기서 생성을 해준다. 그렇기에 내부에서는 코드변경이 일어나지 않아도 되는 것.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    // 멤버 저장소 인터페이스의 구현체를 바꿀려면 이름이 멤버 저장소인 이 메서드만 수정하면 되기때문.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 위와 같은 맥락이다. 할인정책이 변경된다면 해당 부분만 수정하면 되는 것.
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        // 할인 정책을 정률할인정책으로 변경하겠다.
        return new RateDiscountPolicy();
    }

    // 이렇게 의존성을 외부에서 주입시켜주면 매인코드를 건드리지 않고 구현체를 바꿀 수 있다.
    // 메인코드는 생성자를 통해 외부에서 의존성을 주입받기 때문에 interface에만 의존(의존성 역전)하게 된다.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    // 이렇게 리팩토링 하는 이유는 중복이 제거되고 가독성이 높아지기 때문이다.
    // 이전의 방법에서는 memberRepository의 생성자가 2번 들어갔기 때문에
    // MemeberRepository의 구현체가 바뀌면 두부분을 모두 수정해야 한다.
    // 하지만 리팩토링한 방식에서는 메서드 하나만 바꾸면 둘 다 바뀌기에 변화에 더 효율적으로 대처할 수 있다.
}
