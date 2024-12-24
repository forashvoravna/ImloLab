package uzb.lab.imlolab.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "word field must not be blank")
    @Column(nullable = false, unique = true, name = "word")
    String word;

    @Column(nullable = false)
    String description;

    @NotBlank(message = "word_lotin field must not be blank")
    @Column(nullable = false, unique = true, name = "word_lotin")
    String wordLotin;

    @Column(name = "description_lotin", nullable = false)
    String descriptionLotin;

}
