package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // AppConfig은 메인함수 안에서 다룬다. 프로그램의 핵심 로직들에 의존성을 주입해주어야 하니
        // 메인함수 내에서 AppConfig의 내부 메서드를 모두 실행하여 미리 정의해둔 의존성을 주입해주도록 한다.
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // AppConfig을 다음과 같은 코드를 통해 Spring에 적용시킬 수 있다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // getBean의 parameter로 이름을 넘겨야 하는데, 기본적으로 AppConfig 파일에 선언되어있는 메서드 이름을 따라간다고 한다.
        // 두번째 paramter는 type이라고 한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
    }
}
