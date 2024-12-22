package uzb.lab.imlolab.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.payload.TextPayload;
import uzb.lab.imlolab.payload.WordsPayload;
import uzb.lab.imlolab.service.implement.WordsService;

@RequiredArgsConstructor
@RequestMapping("/imlo/words")
@CrossOrigin
@RestController
public class WordsController {

    private final WordsService wordsService;

    @PostMapping("/check-text/")
    public ResponseEntity<ResultDTO> checkTextForWords(@RequestBody TextPayload textPayload) {
        System.out.println("asdasd");
        return wordsService.checkTextForWords(textPayload);
    }


    @GetMapping("/")
    public ResponseEntity<ResultDTO> getAllWords() {
        return wordsService.getAllWords();
    }

    @PostMapping("/")
    public ResponseEntity<ResultDTO> saveWords(@RequestBody @Valid WordsPayload wordsPayload) {
        return wordsService.saveWord(wordsPayload);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResultDTO> findWordById(@PathVariable Long id) {
        return wordsService.findWordById(id);
    }

    @GetMapping("/find-word/{word}")
    public ResponseEntity<ResultDTO> findByWord(@PathVariable String word) {
        return wordsService.findByWord(word);
    }

    @GetMapping("/find-word-list/{word}")
    public ResponseEntity<ResultDTO> findByWords(@PathVariable String word) {
        return wordsService.findByWords(word);
    }

    @PutMapping("/edit-word/by-id/{id}")
    public ResponseEntity<ResultDTO> editWordById(@PathVariable Long id, @RequestBody @Valid WordsPayload wordsPayload) {
        return wordsService.editWordById(id, wordsPayload);
    }

    @PutMapping("/edit-word/by-word/{word}")
    public ResponseEntity<ResultDTO> editByWord(@PathVariable String word, @RequestBody @Valid WordsPayload wordsPayload) {
        return wordsService.editByWord(word, wordsPayload);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ResultDTO> deleteWordById(@PathVariable Long id) {
        return wordsService.deleteById(id);
    }

    @DeleteMapping("/delete-by-word/{word}")
    public ResponseEntity<ResultDTO> deleteByWord(@PathVariable String word) {
        return wordsService.deleteByWord(word);
    }

}
