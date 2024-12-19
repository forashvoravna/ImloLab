package uzb.lab.imlolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uzb.lab.imlolab.service.WordsService;

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
}
