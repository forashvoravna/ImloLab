package uzb.lab.imlolab.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.entity.Words;
import uzb.lab.imlolab.exception.ItemNotFoundException;
import uzb.lab.imlolab.payload.TextPayload;
import uzb.lab.imlolab.payload.WordsPayload;
import uzb.lab.imlolab.repository.WordsRepository;
import uzb.lab.imlolab.service.IWordsService;
import uzb.lab.imlolab.util.AlphabetRecognition;
import uzb.lab.imlolab.util.CleaningWords;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordsService implements IWordsService {

    private final WordsRepository wordsRepository;

    private final ResultDTO resultDTO = new ResultDTO();


    @Override
    public ResponseEntity<ResultDTO> checkTextForWords(TextPayload textPayload) {
        System.out.println("asdasdasd");
        String text = textPayload.getText();

        String s = CleaningWords.textFilter(text);
        System.out.println("text => " + s);
        return null;
    }


    @Override
    public ResponseEntity<ResultDTO> getAllWords() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findAll()));
        } catch (Exception e) {
            log.error("WordsService.getAllWords() => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> saveWord(WordsPayload wordsPayload) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.save(Words.builder().word(wordsPayload.getWord()).description(wordsPayload.getDescription()).wordLotin(wordsPayload.getWordLotin()).descriptionLotin(wordsPayload.getDescriptionLotin()).build())));
        } catch (Exception e) {
            log.error("WordsService.saveWord(WordsPayload wordsPayload) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> findWordById(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findWordsById(id)));
        } catch (Exception e) {
            log.error("WordsService.findWordById(Long id) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> findByWord(String word) {
        try {
            String cleanWord = CleaningWords.cleanFullWord(word);
            word = CleaningWords.cleanWord(word);
            boolean isKrill = AlphabetRecognition.isKirill(cleanWord);
            boolean isLotin = AlphabetRecognition.isLotin(cleanWord);

            if (isKrill && isLotin) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.error("So'z aralash alifbolardan iborat"));
            } else if (isKrill) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findWordsByWord(word)));
            } else if (isLotin) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findWordsByWordLotin(word)));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.error("Bunday so'z bazada mavjud emas"));
            }

        } catch (Exception e) {
            log.error("WordsService.findByWord(String word) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> findByWords(String word) {
        try {
            String cleanWord = CleaningWords.cleanFullWord(word);
            word = CleaningWords.cleanWord(word);
            boolean isKrill = AlphabetRecognition.isKirill(cleanWord);
            boolean isLotin = AlphabetRecognition.isLotin(cleanWord);

            if (isKrill && isLotin) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.error("So'z aralash alifbolardan iborat"));
            } else if (isKrill) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findWordsListByWord(word)));
            } else if (isLotin) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.findWordsListByWordLotin(word)));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDTO.error("Bunday so'z bazada mavjud emas"));
            }

        } catch (Exception e) {
            log.error("WordsService.findByWords(String word) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> editWordById(Long id, WordsPayload wordsPayload) {
        try {
            if (!wordsRepository.existsById(id)) {
                throw new ItemNotFoundException("id not found");
            }
            Words words = wordsRepository.findWordsById(id);
            words.setWord(wordsPayload.getWord());
            words.setDescription(wordsPayload.getDescription());
            words.setWordLotin(wordsPayload.getWordLotin());
            words.setDescriptionLotin(wordsPayload.getDescriptionLotin());

            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.save(words)));
        } catch (Exception e) {
            log.error("WordsService.editWordById(Long id, WordsPayload wordsPayload) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> editByWord(String word, WordsPayload wordsPayload) {
        try {
            if (wordsRepository.existsByWord(word) && wordsRepository.existsByWordLotin(word)) {
                throw new ItemNotFoundException("word not found");
            }
            Words words = wordsRepository.findWordsByWord(word);
            words.setWord(wordsPayload.getWord());
            words.setDescription(wordsPayload.getDescription());
            words.setWordLotin(wordsPayload.getWordLotin());
            words.setDescriptionLotin(wordsPayload.getDescriptionLotin());

            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success(wordsRepository.save(words)));
        } catch (Exception e) {
            log.error("WordsService.editWordById(Long id, WordsPayload wordsPayload) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> deleteById(Long id) {
        try {
            if (!wordsRepository.existsById(id)) {
                throw new ItemNotFoundException("id not found");
            }
            wordsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success("The " + id + "th word has been successfully deleted"));
        } catch (Exception e) {
            log.error("WordsService.deleteById(Long id) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> deleteByWord(String word) {
        try {
            if (wordsRepository.existsByWord(word) && wordsRepository.existsByWordLotin(word)) {
                throw new ItemNotFoundException("word not found");
            }
            Words words = wordsRepository.findWordsByWord(word);
            wordsRepository.delete(words);
            return ResponseEntity.status(HttpStatus.OK).body(resultDTO.success("The " + word + " has been successfully deleted"));
        } catch (Exception e) {
            log.error("WordsService.deleteByWord(String word) => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO.error(e));
        }
    }
}
