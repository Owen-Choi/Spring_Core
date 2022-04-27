package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixDiscountPolicyTest {

    FixDiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Test
    @DisplayName("VIP면 고정할인정책에 따라 1000원이 할인된다.")
    void 할인() {
        Member member = new Member(10L, "memberVIP", Grade.VIP);
        int discount = discountPolicy.discount(member, 11000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void 미할인() {
        Member member = new Member(10L, "memberBASIC", Grade.BASIC);
        int discount = discountPolicy.discount(member, 11000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}
