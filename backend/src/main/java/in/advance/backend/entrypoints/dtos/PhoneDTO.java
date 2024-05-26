package in.advance.backend.entrypoints.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PhoneDTO {
    private String number;
    private String cityCode;
    private String countryCode;
}
