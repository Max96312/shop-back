package max.shop.service;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.common.exception.UserNotFoundException;
import max.shop.domain.User;
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
    public String join(User user) {
        validateDuplicateUser(user); // 중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());
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
