package in.advance.backend.entrypoints.mappers;

import in.advance.backend.commons.JWTHelper;
import in.advance.backend.domain.models.Phone;
import in.advance.backend.domain.models.User;
import in.advance.backend.entrypoints.dtos.LoginDTO;
import in.advance.backend.entrypoints.dtos.RegisterDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {
    public User mapRegisterDTOToUser(RegisterDTO registerDTO){
        var now = LocalDateTime.now();
        return User.builder()
                .name(registerDTO.getName())
                .token(JWTHelper.generateToken(registerDTO.getEmail()))
                .password(registerDTO.getPassword())
                .created(now)
                .modified(now)
                .email(registerDTO.getEmail())
                .lastLogin(now)
                .isActive(Boolean.TRUE)
                .phones(
                        registerDTO.getPhones().stream()
                            .map(phone -> Phone.builder()
                                    .countryCode(phone.getCountryCode())
                                    .cityCode(phone.getCityCode())
                                    .number(phone.getNumber())
                                    .build())
                                .toList()
                )
                .build();
    }
    public User mapLoginDTOToUser(LoginDTO loginDTO){
        return User.builder()
                .password(loginDTO.getPassword())
                .email(loginDTO.getEmail())
                .build();
    }
}
