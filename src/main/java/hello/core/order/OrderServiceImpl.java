package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // id로 멤버를 먼저 찾고, 멤버의 등급에 따라 할인을 다르게 적용해야 하므로 이렇게 2개의 인스턴스를 만든다.
    private MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 고정할인방식에서 가변할인방식으로 변경되었기 때문에 구현클래스를 바꾼다.
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 하지만 이런 방식은 DIP 방식에 위배된다. 구현체가 바뀔 때 마다 코드를 수정해야 하기 떄문.
    // 따라서 다음과 같이 수정한 뒤 AppConfig 클래스를 만든다. 이대로 사용하면 null pointer exception이 발생하기 때문.
    private DiscountPolicy discountPolicy;

    // 생성자 수준에서 일어나는 의존관계 주입이다.
    // 컴포넌트 스캔에서 가장 먼저 의존관계가 주입된다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // setter 의존관계 주입이다.
    // setter 의존관계 주입은 생성자 의존관계 주입 이후에 일어난다.
    // setter 의존관계 주입을 사용하는 이유는 선택적으로 의존관계를 주입하거나(required = false)
    // 나중에 변경할 수 있기 때문이다. (실무에서 이런 일은 거의 없다고 한다.)
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
