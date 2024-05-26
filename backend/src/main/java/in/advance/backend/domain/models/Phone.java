package in.advance.backend.domain.models;

import in.advance.backend.database.entitites.UserDao;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
    private UserDao user;
}
