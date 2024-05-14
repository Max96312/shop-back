package max.shop.service;

import jakarta.transaction.Transactional;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.domain.User;
import max.shop.domain.type.Gender;
import max.shop.dto.request.user.UserRegisterForm;
import max.shop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    void 회원가입() throws Exception {
        //given
        UserRegisterForm form = UserRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
        User user = User.createUser(form);

        //when
        String savedId = userService.join(user);

        //then
        assertEquals(user, userRepository.findById(savedId).get());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        UserRegisterForm form = UserRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
        User user = User.createUser(form);
        UserRegisterForm form2 = UserRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
        User user2 = User.createUser(form);

        //when
        String savedId = userService.join(user);

        //then
        assertThrows(UserNameDuplicatedException.class, () -> userService.join(user2));
    }
}