package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// getter와 setter, tostring을 어노테이션으로 등록해놓으면 이것들을 만들 필요 없이
// 롬복 라이브러리가 자동으로 만들어준다.
@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        // 이 Lombok이라는 녀석은 getter와 setter, constructor 등을 만들어주고
        // 개발자에게 여러 편의를 제공해주는 라이브러리이다.
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");
        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println(helloLombok);
    }
}
