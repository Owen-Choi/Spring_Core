package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void at() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testBean.class);
    }

    static class testBean {

        // 자동 의존관계 주입 Autowired가 bean이 없는 상황에서 어떻게 동작하는지 확인하기 위한 test code이다.

        // required = false인 경우에는 bean이 없을 경우에 아예 메서드가 호출되지 않는다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable 어노테이션을 사용할 경우 bean이 Null이어도 메서드는 호출된다.
        // 여기서 noBean2는 null이다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // java8의 Optional을 사용하면 마찬가지로 Bean이 없어도 메서드를 호출할 수 있다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
