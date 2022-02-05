package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
