package hello.core.singleton;

public class StatefulService {

    // 이렇게 정적으로, 값이 공유되는 필드가 존재할 경우 심각한 문제가 생길 수 있음.
    //private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        //this.price = price;     //오류 발생지점
        return price;
    }
    // 위와 같이 설계할 경우에는 아래의 getPrice의 사용 대신
    // int UserAPrice = statefulService1.order("userA", 10000); 과 같이 사용해야 한다.

}
