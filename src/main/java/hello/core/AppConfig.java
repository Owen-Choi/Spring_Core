package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // MemberServiceImpl을 외부(AppConfig)에서 생성을 해주고, 원래 MemberServiceImpl 내부에서 생성해줬던
    // MemoryMemberRepository도 여기서 생성을 해준다. 그렇기에 내부에서는 코드변경이 일어나지 않아도 되는 것.
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    // 이렇게 의존성을 외부에서 주입시켜주면 매인코드를 건드리지 않고 구현체를 바꿀 수 있다.
    // 메인코드는 생성자를 통해 외부에서 의존성을 주입받기 때문에 interface에만 의존(의존성 역전)하게 된다.
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
