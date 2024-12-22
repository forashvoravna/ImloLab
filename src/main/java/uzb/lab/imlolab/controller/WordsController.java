package uzb.lab.imlolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uzb.lab.imlolab.service.WordsService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/words")
@CrossOrigin
@RestController
public class WordsController {

    private final WordsService wordsService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getALlWords(){
        return ResponseEntity.status(200).body(wordsService.getAllWords());
    }

    @GetMapping("/incorrect")
    public List<String> getIncorrect(@RequestParam String text){
        return wordsService.findIncorrectWords(text);
    }
}
