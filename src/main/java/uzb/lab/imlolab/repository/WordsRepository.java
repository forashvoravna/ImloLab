package uzb.lab.imlolab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzb.lab.imlolab.entity.Words;

public interface WordsRepository extends JpaRepository<Words, Integer> {
}
