package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;


    // 깨알 팁 : ctrl + shift + t 를 눌러서 바로 test class를 만들자.
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        else
            return 0;
    }
}
