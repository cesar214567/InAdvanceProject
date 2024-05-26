package in.advance.backend.entrypoints.dtos;

import in.advance.backend.database.entitites.PhoneDao;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
