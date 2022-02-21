package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    // 만약 고객의 선택으로 고정 할인정책과 변동 할인정책을 바꿀 수 있다면
    // 조회한 스프링 빈이 모두 필요한 상황일 것이다.
    @Test
    void findAllBean() {
        // AutoAppConfig을 추가해줌으로서 기존의 스프링 빈들을 모두 읽어드린다. 여기서는 할인 정책에 접근하기 위해서 추가했다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        int RatediscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(discountPrice).isEqualTo(1000);
        Assertions.assertThat(RatediscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        // policyMap 변수는 모든 DiscountPolicy를 다 주입받는다. 즉 fixDiscountPolicy, rateDiscountPolicy를 모두 주입받는다.
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            // 여기서 discountCode가 Fix 할인 정책인지 Rate 할인 정책인지 식별하여 반환해준다.
            // 아래의 코드는 원하는 할인 정책에 따른 할인가를 반환해준다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
