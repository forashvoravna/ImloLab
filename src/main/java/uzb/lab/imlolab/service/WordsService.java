package uzb.lab.imlolab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uzb.lab.imlolab.entity.Words;
import uzb.lab.imlolab.repository.WordsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordsService {

    private final WordsRepository wordsRepository;

    public List<Words> getAllWords(){
        try {
            return wordsRepository.findAll();
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
