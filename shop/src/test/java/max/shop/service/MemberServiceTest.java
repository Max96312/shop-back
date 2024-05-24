package max.shop.service;

import jakarta.transaction.Transactional;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.domain.Member;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.member.MemberRegisterResponse;
import max.shop.repository.MemberRepository;
import max.shop.serviceImp.MemberServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberServiceImp memberService;
    @Autowired private MemberRepository memberRepository;

    @Test
    void 회원_가입_테스트(){
        //given
        MemberRegisterForm testRequest = new MemberRegisterForm("testId", "qwer1234!", "testName", "010-1234-1234", "test@gmail.com");
        MemberRegisterResponse savedId = memberService.join(testRequest);

        //when
        Optional<Member> findMember = memberRepository.findById(savedId.getId());

        //then
        Assertions.assertTrue(findMember.isPresent(), "Member가 저장되어야 한다.");
        Assertions.assertEquals(savedId.getId(), findMember.get().getId(), "Member가 일치해야 한다.");
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        MemberRegisterForm testRequest1 = new MemberRegisterForm("testId", "qwer1234!", "testName", "010-1234-1234", "test@gmail.com");
        MemberRegisterForm testRequest2 = new MemberRegisterForm("testId", "qwer1234!", "testName", "010-1234-1234", "test@gmail.com");

        //when
        MemberRegisterResponse savedId1 = memberService.join(testRequest1);

        //then
        assertThrows(UserNameDuplicatedException.class, () -> memberService.join(testRequest2));
    }
}