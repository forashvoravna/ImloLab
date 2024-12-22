package uzb.lab.imlolab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uzb.lab.imlolab.enums.AccountRoles;
import uzb.lab.imlolab.enums.GeneralStatus;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "name field must not be blank")
    @Column(nullable = false, name = "name")
    String name;

    @NotBlank(message = "email field must not be blank")
    @Email(message = "email field must not be blank")
    @Column(unique = true, nullable = false, name = "email")
    String email;

    @JsonIgnore
    @Column(name = "roles")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    AccountRoles role = AccountRoles.ROLE_USER;

    @JsonIgnore
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    GeneralStatus status = GeneralStatus.ACTIVE;

    @JsonIgnore
    @Column(name = "visible")
    @Builder.Default
    Boolean visible = Boolean.TRUE;

    @JsonIgnore
    @CreationTimestamp
    Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    Date updatedAt;

}
