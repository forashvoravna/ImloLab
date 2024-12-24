package uzb.lab.imlolab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.exception.AppBadRequestException;
import uzb.lab.imlolab.exception.ItemNotFoundException;

@CrossOrigin
@ControllerAdvice
public class ExceptionHandlerController {

    private final ResultDTO result = new ResultDTO();

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResultDTO> handler(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(result.error(e));
    }

    @ExceptionHandler({AppBadRequestException.class})
    public ResponseEntity<ResultDTO> handlerBadRequest(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(result.error(e));
    }

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ResultDTO> handlerNotFound(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result.error(e));
    }

}