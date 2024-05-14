package max.shop.service;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.common.exception.UserNotFoundException;
import max.shop.domain.User;
import max.shop.dto.request.user.Address;
import max.shop.dto.request.user.UserRegisterForm;
import max.shop.dto.response.UserResponse;
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
    public UserResponse join(UserRegisterForm form) {
        validateDuplicateUser(form.getUserId()); // 중복 회원 검증
        User savedUser = userRepository.save(User.createUser(form));
        return savedUser.toDto(savedUser);
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
