package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 스프링 인터페이스에 의존하여 초기화, 소멸 메서드를 정의할 수 있다.
// 더 좋은 방법이 많이 있어서 현재에는 거의 사용하지 않는 방법이라고 한다.
public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 빈의 초기화에 관련된 메서드이다.
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }

    // 빈의 소멸과 관련된 메서드이다.
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }

    // 스프링이 제공하는 인터페이스에 의존하지 않고 따로 메서드를 정의해서 초기화와 소멸 콜백을 진행하는 방법.
    // 외부에서도 사용할 수 있어서 많이 사용되는 방법이다.
    // @Bean(initMethod = "Init", destroyMethod = "close") 와 같이 사용한다.
    @PostConstruct
    public void Init() {
        System.out.println("NetworkClient.Init");
        connect();
        call("초기화 연결 메세지");
    }

    // 가장 권장되는 방법. 그냥 어노테이션만 붙이면 해결된다.
    // 하지만 외부 라이브러리에서는 사용할 수 없으므로 그럴 경우에는 Bean(initMethod = "" ... ) 방법을 사용하도록 하자.
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
