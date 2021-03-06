package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // DIP를 잘 준수한다. 여기서는 MemberRepository를 누가 구체화할지 전혀 모르기때문에
    // 인터페이스에만 의존하는 의존성 역전이 일어남.
    private final MemberRepository memberRepository;

    // @Autowired를 사용하는 이유 :
    // 기존 AppConfig 클래스에서 memberRepository의 구현체로 memory를 사용하겠다고 명시를 해두었는데
    // AutoAppConfig 클래스에서는 어디에도 구현체를 명시하지 않기 때문에 자동 의존관계 주입이 필요하다.

    // 이렇게 생성자 수준에서 의존관계를 주입해주는 것을 생성자 주입이라고 한다.
    // component scan이 일어날때 가장 먼저 이 생성자에 있는 의존관계를 주입한다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
