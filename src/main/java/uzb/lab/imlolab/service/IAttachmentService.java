package uzb.lab.imlolab.service;

import org.springframework.core.io.FileUrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uzb.lab.imlolab.dto.ResultDTO;

public interface IAttachmentService {

    ResponseEntity<ResultDTO> saveAttachment(MultipartFile multipartFile);

    ResponseEntity<FileUrlResource> preview(String hashId);

    ResponseEntity<FileUrlResource> download(String hashId);

    ResponseEntity<ResultDTO> removeAttachmentByHashId(String hashId);
}
