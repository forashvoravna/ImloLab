package uzb.lab.imlolab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uzb.lab.imlolab.entity.Words;
import uzb.lab.imlolab.repository.WordsRepository;

import java.util.ArrayList;
import java.util.Arrays;
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

    // Berilgan matnda noto'g'ri so'zlarni qaytarish
    public List<String> findIncorrectWords(String text) {
        List<String> incorrectWords = new ArrayList<>();
        String[] words = text.split("[\\s\\-]+");
        for (String word : words) {
            String cleanWord = word.toLowerCase().replaceAll("[^a-zA-Zʼ]", ""); // So'zni tozalash
            if (!cleanWord.isEmpty() && !wordsRepository.existsByWordLotin(cleanWord)) {
                incorrectWords.add(word); // Agar lug'atda topilmasa, noto'g'ri so'zga qo'shamiz
            }
        }
        return incorrectWords;
    }
}
