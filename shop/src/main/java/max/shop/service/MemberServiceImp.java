package max.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import max.shop.common.exception.LoginFailureException;
import max.shop.common.exception.UserNameDuplicatedException;
import max.shop.common.exception.UserNotFoundException;
import max.shop.domain.Member;
import max.shop.dto.request.member.MemberLoginForm;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.member.MemberRegisterResponse;
import max.shop.repository.MemberRepository;
import max.shop.serviceImp.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Override
    @Transactional
    public MemberRegisterResponse join(MemberRegisterForm form) {
        validateDuplicateUser(form.getId());
        Member savedMember = memberRepository.save(Member.createUser(form));
        return new MemberRegisterResponse(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    }

    /**
     * 로그인
     */
//    @Override
//    @Transactional
//    public MemberAccountDto loginUser(MemberLoginForm form) {
//        Member findMember = memberRepository.findById(form.getUserId()).orElseThrow(UserNotFoundException::new);
//        if (!findMember.getPassword().equals(form.getPassword())) {
//            throw new LoginFailureException();
//        }
//        return new MemberAccountDto(user.getId(), user.getUserName(), user.getPhoneNumber());
//    }

    /**
     * 회원 정보 수정
     */
//    public MemberAccountDto updateUser(UserUpdateForm form) {
//        Member findUser = userRepository.findById(form.getUserId()).orElseThrow(UserNotFoundException::new);
//        findUser.changeName(form.getUserName());
//        findUser.changePhoneNumber(form.getPhoneNumber());
//        findUser.changePassword(form.getPassword());
//        return MemberAccountDto.from(findUser);
//    }

    /**
     * 회원 탈퇴
     */
//    @Transactional
//    public void deleteUser(MemberLoginForm form){
//        Member user = userRepository.findById(form.getUserId()).orElseThrow(UserNotFoundException::new);
//        if (!user.getPassword().equals(form.getPassword())) {
//            throw new LoginFailureException();
//        }
//        userRepository.delete(user);
//        log.info("삭제된 회원 아이디 = {}", user.getId());
//    }


    //회원 전체 조회
//    public List<Member> findUsers(){
//        return userRepository.findAll();
//    }
//
//    public Member findOne(String id) {
//        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//    }
//
    private void validateDuplicateUser(String userId) {
        Optional<Member> findUser = memberRepository.findById(userId);
        if (!findUser.isEmpty()) {
            throw new UserNameDuplicatedException("이미 존재하는 회원입니다.");
        }
    }
}
