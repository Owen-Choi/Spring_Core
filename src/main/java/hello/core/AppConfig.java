package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServieImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServieImpl();
    }
}
