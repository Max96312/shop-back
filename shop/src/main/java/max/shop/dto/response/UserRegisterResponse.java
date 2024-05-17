package max.shop.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import max.shop.dto.UserAccountDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterResponse {
    String userId;

    public static UserRegisterResponse from(UserAccountDto dto) {
        return new UserRegisterResponse(dto.getUserId());
    }
}
