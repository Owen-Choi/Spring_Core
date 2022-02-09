package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // DIP를 잘 준수한다. 여기서는 MemberRepository를 누가 구체화할지 전혀 모르기때문에
    // 인터페이스에만 의존하는 의존성 역전이 일어남.
    private final MemberRepository memberRepository;

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
