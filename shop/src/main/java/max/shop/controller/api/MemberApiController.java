package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.EmailPatternException;
import max.shop.common.exception.PasswordPatternException;
import max.shop.dto.request.member.MemberLoginForm;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.SingleResult;
import max.shop.dto.response.member.MemberLoginResponse;
import max.shop.dto.response.member.MemberRegisterResponse;
import max.shop.service.MemberServiceImp;
import max.shop.service.response.ResponseService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {
    private final MemberServiceImp memberService;
    private final ResponseService responseService;

    /**
     * 회원 가입
     */
    @PostMapping("/join")
    public SingleResult<MemberRegisterResponse> register(@RequestBody @Validated MemberRegisterForm form
                                    , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email"))
            throw new EmailPatternException();
        if(bindingResult.hasFieldErrors("password"))
            throw new PasswordPatternException();

        return responseService.handleSingleResult(memberService.join(form));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public SingleResult<MemberLoginResponse> login(@RequestBody MemberLoginForm form) {
        return responseService.handleSingleResult(memberService.login(form));
    }

    /**
     * 회원 정보 수정
     * - 이름 변경
     * - 전화 번호 수정
     * - 주소 변경
     */
    //TODO: 주소 추가 기능 -> api 분리
//    @PutMapping("/{memberId}/edit")
//    public SingleResult<MemberUpdateResponse> updateName(@RequestBody MemberUpdateForm form){
//        memberService.updateMemberName(form);
//        return new UserUpdateResponse();
//    }

    /**
     * 회원 탈퇴
     */
//    @DeleteMapping("/{memberId}")
//    public Result deleteUser(@PathVariable("memberId") String memberId){
//        memberService.deleteMember(memberId);
//        return responseService.handleSuccessResult();
//    }
}
