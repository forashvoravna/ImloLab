package uzb.lab.imlolab.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Words {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    @Column(nullable = false)
    String word;
    @Column(nullable = false)
    String description;
    @Column(nullable = false, name = "word_lotin")
    String wordLotin;
    @Column(nullable = false, name = "description_lotin")
    String descriptionLotin;



}
