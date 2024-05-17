package max.shop.service;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.LoginFailureException;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.common.exception.UserNotFoundException;
import max.shop.domain.User;
import max.shop.dto.request.user.UserLoginForm;
import max.shop.dto.request.user.UserRegisterForm;
import max.shop.dto.UserAccountDto;
import max.shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public UserAccountDto join(UserRegisterForm form) {
        validateDuplicateUser(form.getUserId()); // 중복 회원 검증
        User savedUser = userRepository.save(User.createUser(form));
        return new UserAccountDto(savedUser.getId(), savedUser.getUsername());
    }

    /**
     * 로그인
     */
    @Transactional
    public UserAccountDto loginUser(UserLoginForm form) {
        User user = userRepository.findById(form.getUserId()).orElseThrow(UserNotFoundException::new);
        if (!user.getPassword().equals(form.getPassword())) {
            throw new LoginFailureException();
        }
        return new UserAccountDto(user.getId(), user.getUsername());
    }

    private void validateDuplicateUser(String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if (!findUser.isEmpty()) {
            throw new UserNameDuplicatedException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
