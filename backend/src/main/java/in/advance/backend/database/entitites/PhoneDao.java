package in.advance.backend.database.entitites;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PhoneDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 10)
    private String number;

    @Column(length = 10)
    private String cityCode;

    @Column(length = 10)
    private String countryCode;
}
