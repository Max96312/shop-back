package max.shop.serviceImp;

import max.shop.dto.request.member.MemberLoginForm;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.member.MemberLoginResponse;
import max.shop.dto.response.member.MemberRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    MemberRegisterResponse join(MemberRegisterForm form);
    MemberLoginResponse login(MemberLoginForm form);
}
