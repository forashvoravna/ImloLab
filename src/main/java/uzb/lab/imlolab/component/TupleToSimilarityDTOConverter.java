package uzb.lab.imlolab.component;

import jakarta.persistence.Tuple;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uzb.lab.imlolab.dto.SimilarityDTO;

@Component
public class TupleToSimilarityDTOConverter implements Converter<Tuple, SimilarityDTO> {
    @Override
    public SimilarityDTO convert(Tuple source) {
        String wordLotin = (String) source.get(0); // 0-indeksda `word_lotin` bo'ladi
        Double similarity = (Double) source.get(1); // 1-indeksda `similarity` bo'ladi
        return new SimilarityDTO(wordLotin, similarity);
    }
}