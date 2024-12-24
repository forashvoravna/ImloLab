package uzb.lab.imlolab.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class WordsDTO {
    String word;
    boolean status;
    Object data;
    String e;

    public WordsDTO(String word, boolean status, Object data, String e) {
        this.word = word;
        this.status = status;
        this.data = data;
        this.e = e;
    }

    public WordsDTO(String word, boolean status, String e) {
        this.word = word;
        this.status = status;
        this.e = e;
    }

    public WordsDTO successful(String word) {
        return new WordsDTO(word, true, "success");
    }

    public WordsDTO error(String word, Object data) {
        return new WordsDTO(word, false, data, "not found");
    }
}