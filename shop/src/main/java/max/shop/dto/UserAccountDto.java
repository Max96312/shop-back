package max.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import max.shop.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String userId;
    private String name;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime createdAt;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime modifiedAt;

    public static UserAccountDto from(User entity) {
        return new UserAccountDto(entity.getId(),
                entity.getUsername()
//                ,entity.getCreatedAt(),
//                entity.getUpdatedAt()
        );
    }
}
