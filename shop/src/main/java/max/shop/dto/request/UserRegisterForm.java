package max.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import max.shop.domain.type.Gender;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterForm {
    @NotBlank(message = "회원 아이디는 필수 입력란입니다.")
    @Length(max = 20, message = "회원 아이디의 길이는 최대 20자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-0]*$", message = "회원 아이디는 영어랑 숫자만 가능합니다.")
    String userId;

    @NotBlank(message = "닉네임은 필수 입력란입니다.")
    @Length(max = 20, message = "닉네임 길이는 최대 20자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-0]*$", message = "닉네임은 영어랑 숫자만 가능합니다.")
    String username;

    @Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩" +
                    " 포함된 8 ~20자의 비밀번호여야 합니다.")
    String password;
    String phoneNumber;
    LocalDateTime birthday;
    Gender gender;
}
