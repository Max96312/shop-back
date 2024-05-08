package max.shop.domain.embed;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String state;
    private String street;
    private String zip;

    public Address(String city, String state, String street, String zip) {
        this.city = city;
        this.street = street;
        this.state = state;
        this.zip = zip;
    }
}