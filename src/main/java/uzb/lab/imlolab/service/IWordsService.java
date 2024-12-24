package uzb.lab.imlolab.service;

import org.springframework.http.ResponseEntity;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.payload.TextPayload;
import uzb.lab.imlolab.payload.WordsPayload;

public interface IWordsService {

    ResponseEntity<ResultDTO> checkTextForWords(TextPayload textPayload);

    ResponseEntity<ResultDTO> getAllWords();

    ResponseEntity<ResultDTO> saveWord(WordsPayload wordsPayload);

    ResponseEntity<ResultDTO> findWordById(Long id);

    ResponseEntity<ResultDTO> findByWord(String word);

    ResponseEntity<ResultDTO> findByWords(String word);

    ResponseEntity<ResultDTO> editWordById(Long id, WordsPayload wordsPayload);

    ResponseEntity<ResultDTO> editByWord(String word, WordsPayload wordsPayload);

    ResponseEntity<ResultDTO> deleteById(Long id);

    ResponseEntity<ResultDTO> deleteByWord(String word);
}
