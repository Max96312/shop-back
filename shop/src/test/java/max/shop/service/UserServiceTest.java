//package max.shop.service;
//
//import jakarta.transaction.Transactional;
//import max.shop.common.exception.UserNameDuplicatedException;
//import max.shop.domain.Member;
//import max.shop.dto.request.member.MemberRegisterForm;
//import max.shop.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class UserServiceTest {
//
//    @Autowired private MemberServiceImp userService;
//    @Autowired private MemberRepository userRepository;
//
//    @Test
//    void 회원가입() throws Exception {
//        //given
//        MemberRegisterForm form = MemberRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
//        Member user = Member.createUser(form);
//
//        //when
//        String savedId = userService.join(user);
//
//        //then
//        assertEquals(user, userRepository.findById(savedId).get());
//    }
//
//    @Test
//    void 중복_회원_예외() throws Exception {
//        //given
//        MemberRegisterForm form = MemberRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
//        Member user = Member.createUser(form);
//        MemberRegisterForm form2 = MemberRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
//        Member user2 = Member.createUser(form);
//
//        //when
//        String savedId = userService.join(user);
//
//        //then
//        assertThrows(UserNameDuplicatedException.class, () -> userService.join(user2));
//    }
//}