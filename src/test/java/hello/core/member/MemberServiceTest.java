package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServieImpl();

    @Test
    void join() {
        //give : ~~환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when : ~~했을때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then : ~~된다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
