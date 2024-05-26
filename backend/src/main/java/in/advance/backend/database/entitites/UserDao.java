package in.advance.backend.database.entitites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email not valid")
    private String email;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private String token;

    @Column
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<PhoneDao> phones;


}
