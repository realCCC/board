package com.study.board.service;


import com.study.board.domain.Member;
import com.study.board.repository.MemberRepository;
import com.study.board.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행한다
@Transactional // 테스트케이스에 이 어노테이션이 있으면 테스트 시작전 트랙잭션을 시작하고 완료후 항상 롤백한다
    //이렇게 하면 DB에 데이터가 남지 않아 다음 테스트에 영향을 주지 않는다
        // 이 어노테이션을 지우고 실행해보면 DB에 자료가 저장되어있음
        // 그래서 테스트 할때는 이 어노테이션을 사용해 DB에 데이터가 남지 않게 해준다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given  무언가가 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when 요거 실행했을때
        Long saveId = memberService.join(member);

        //then  결과가 이게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



    }

}