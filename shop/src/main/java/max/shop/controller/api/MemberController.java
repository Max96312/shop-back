package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.dto.request.member.MemberLoginForm;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.SingleResult;
import max.shop.dto.response.member.MemberRegisterResponse;
import max.shop.service.MemberServiceImp;
import max.shop.service.response.ResponseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImp memberService;
    private final ResponseService responseService;

    /**
     * 회원 가입
     */
    @PostMapping("/join")
    public SingleResult<MemberRegisterResponse> register(@RequestBody @Validated MemberRegisterForm form
//                                    , BindingResult bindingResult
    ) {
//        if (bindingResult.hasFieldErrors("email"))
//            throw new EmailPatternException();
//        if(bindingResult.hasFieldErrors("password"))
//            throw new PasswordPatternException();

        return responseService.handleSingleResult(memberService.join(form));
    }

    /**
     * 로그인
     */
//    @PostMapping("/sign/login")
//    public MemberLoginResponse login(@RequestBody MemberLoginForm form) {
//        MemberAccountDto loginUser = memberService.loginUser(form);
//        return new MemberLoginResponse(loginUser.getUserId(), loginUser.getUserName());
//    }

    /**
     * 회원 정보 수정
     * - 이름 변경
     * - 전화 번호 수정
     * - 주소 변경
     */
    //TODO: 주소 추가 기능 -> api 분리
//    @PutMapping("user/{userId}/edit")
//    public UserUpdateResponse updateName(@RequestBody UserUpdateForm form){
//        userService.updateUser(form);
//        return new UserUpdateResponse();
//    }

    /**
     * 회원 탈퇴
     */
//    @PostMapping("user/{userId}/delete")
//    public void deleteUser(@RequestBody MemberLoginForm form){
//        userService.deleteUser(form);
//    }
}
