package max.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import max.shop.domain.type.CategoryType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categories {
    @Id
    @GeneratedValue
    @Column(name="categories_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
}
