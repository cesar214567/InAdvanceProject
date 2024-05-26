package in.advance.backend.entrypoints.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginDTO {
    private String email;
    private String password;
}
