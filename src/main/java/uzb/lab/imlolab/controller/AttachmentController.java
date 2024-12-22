package uzb.lab.imlolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.service.implement.AttachmentService;


@CrossOrigin
@RestController
@RequestMapping("/imlo/file")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;


    @PostMapping("/save")
//    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ResultDTO> saveAttachment(@RequestParam(value = "file") MultipartFile multipartFile) {
        return attachmentService.saveAttachment(multipartFile);
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity<FileUrlResource> preview(@PathVariable String hashId) {
        return attachmentService.preview(hashId);
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<FileUrlResource> download(@PathVariable String hashId) {
        return attachmentService.download(hashId);
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity<ResultDTO> delete(@PathVariable String hashId) {
        return attachmentService.removeAttachmentByHashId(hashId);
    }
}
