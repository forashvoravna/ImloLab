package uzb.lab.imlolab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uzb.lab.imlolab.entity.Words;

import java.util.List;


@Repository
public interface WordsRepository extends JpaRepository<Words, Long> {

    @Query(value = "SELECT * FROM  words  WHERE LOWER(word) LIKE LOWER(CONCAT('%', :word, '%'))", nativeQuery = true)
    List<Words> findWordsListByWord(@Param(value = "word") String word);

    @Query(value = "SELECT * FROM  words  WHERE LOWER(wordLotin) LIKE LOWER(CONCAT('%', :wordLotin, '%'))", nativeQuery = true)
    List<Words> findWordsListByWordLotin(@Param(value = "wordLotin") String wordLotin);

    Words findWordsById(Long id);

    Words findWordsByWord(String word);

    Words findWordsByWordLotin(String wordLotin);

    boolean existsById(Long id);

    boolean existsByWord(String word);

    boolean existsByWordLotin(String wordLotin);
}
