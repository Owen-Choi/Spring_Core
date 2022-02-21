package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 아래의 어노테이션이 수행하는 기능은 final로 선언된 변수들의 생성자를 만들어주는 것이다.
// 생성자 주입과 연관지어서 굉장히 편리하기 때문에 롬복 라이브러리의 이 기능은 실무에서 많이 사용한다고 한다.
// 주석을 풀면 오류가 발생하는데, 아래에서 정의된 생성자와 롬복이 만드는 생성자가 일치해서 중복되기 때문이다.
// 이 기능의 장점은, 의존관계 생성이 굉장히 편리해진다는 것이다.
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // id로 멤버를 먼저 찾고, 멤버의 등급에 따라 할인을 다르게 적용해야 하므로 이렇게 2개의 인스턴스를 만든다.
    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 고정할인방식에서 가변할인방식으로 변경되었기 때문에 구현클래스를 바꾼다.
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 하지만 이런 방식은 DIP 방식에 위배된다. 구현체가 바뀔 때 마다 코드를 수정해야 하기 떄문.
    // 따라서 다음과 같이 수정한 뒤 AppConfig 클래스를 만든다. 이대로 사용하면 null pointer exception이 발생하기 때문.
    private final DiscountPolicy discountPolicy;

    // 생성자 수준에서 일어나는 의존관계 주입이다.
    // 컴포넌트 스캔에서 가장 먼저 의존관계가 주입된다.
    // 이 생성자 주입을 사용해야 테스트 코드를 작성하거나 할때 문제가 없다.
    // 수정자 주입을 사용할 경우 테스트 코드 작성이 머리아파진다.
    // 또한 생성자 주입을 선택하면 final 예약어를 사용할 수 있다. 안정성이 더 높아짐.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // setter 의존관계 주입이다.
    // setter 의존관계 주입은 생성자 의존관계 주입 이후에 일어난다.
    // setter 의존관계 주입을 사용하는 이유는 선택적으로 의존관계를 주입하거나(required = false)
    // 나중에 변경할 수 있기 때문이다. (실무에서 이런 일은 거의 없다고 한다.)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

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
