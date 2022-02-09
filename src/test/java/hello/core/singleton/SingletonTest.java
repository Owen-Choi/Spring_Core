package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberSerivce2 = appConfig.memberService();

        // 실행 결과로 서로 다른 객체가 생성됨을 알 수 있다.
        // 즉 고객 트래픽이 초당 100이라면 초당 100개의 객체를 생성하게 된다. 너무 비효율적인 방식.
        System.out.println("memberSerivce1 = " + memberService1);
        System.out.println("memberSerivce2 = " + memberSerivce2);

        // memberService1 != memberService2 검증
        Assertions.assertThat(memberService1).isNotSameAs(memberSerivce2);

        // 싱글톤 패턴이란 이런 경우에서 객체를 딱 1개만 생성하고 공유하도록 설계하는 것을 의미한다.
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new SingletonService
        // 는 컴파일 오류 발생.
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        //instance1 = hello.core.singleton.SingletonService@52e677af
        //instance2 = hello.core.singleton.SingletonService@52e677af
        // 실행 결과이다. 두 인스턴스가 동일한 것을 알 수 있다.
        Assertions.assertThat(instance1).isSameAs(instance2);
        // isSameAs : 자바의 == 연산자와 동일한 역할. 정말 인스턴스가 같은 지를 본다.
        // isEqualTo : 자바의 equals 메서드와 동일한 역할.
    }
}
