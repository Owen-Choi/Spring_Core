package hello.core.singleton;

public class SingletonService {

    // private static으로 선언되어 있으면 java에서 실행 후 이를 인지하고 instance에 값을 넣어둔다고 한다.
    private static SingletonService instance = new SingletonService();

    // 이렇게 하면 외부에서 instance를 만들 수 없고 오직 이 getInstance 메서드를 호출해서만 인스턴스를 얻을 수 있다.
    // 클라이언트들이 얻는 객체가 통합되는것.
    public static SingletonService getInstance() {
        return instance;
    }
    // 하지만 코드가 위와 같으면 인스턴스의 중복생성을 막을 수는 없다.
    // 따라서 private으로 생성자를 만들어준다.
    // 생성자가 private일 경우 싱글톤을 떠올리자.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    // 정리하면, SingletonService의 인스턴스는 SignletonService 클래스 내부에서만 만들 수 있으며(private 생성자,
    // private static 타입의 instance) 단 하나만 생성이 된다. 이 생성이 된 유일한 인스턴스는 외부에서 자유롭게
    // 호출할 수 있지만, 외부에서는 절대로 다른 인스턴스를 만들 수 없는 구조이다.
    // 하지만 단점이 명확하다. 코드가 길어지고, 객체지향의 원칙이 잘 지켜지지 않으며 유연성이 떨어지는 문제점을 가지고 있다.

    // 스프링의 컨테이너는 싱글톤 패턴을 자동으로 적용해준다고 한다.
    // 또한 스프링의 싱글톤 패턴은 위의 단점을 모두 보완하면서 장점을 제공한다고 한다.
}
