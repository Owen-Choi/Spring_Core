package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 동일한 타입이 2개 이상이면 오류 혹은 예기치 못한 결과 발생.
    // 따라서 이름과 타입을 함께 매개변수로 넘기는 방식이 가장 좋다.
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구체 타입으로 조회는 좋은 코드가 아니다.
    // 추상화에 의존해야지 구체화에 의존하면 안되기 때문.
    // 하지만 모든 것이 이상적으로 돌아갈 수는 없으므로 가끔씩 사용하는 방식이라고 한다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("없는 빈 조회")
    void findBeanByNameX() {
        //MemberService xxxxx = ac.getBean("XXXXX", MemberService.class);
        // assertThrows는 매개변수로 넘어간 람다의 로직이 실행되었을 때 특정 예외가 발생해야 오류가 발생하지 않음.
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXXX", MemberService.class));
    }

}
