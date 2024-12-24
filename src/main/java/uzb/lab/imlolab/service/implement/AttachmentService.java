package uzb.lab.imlolab.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uzb.lab.imlolab.dto.ResultDTO;
import uzb.lab.imlolab.entity.Attachment;
import uzb.lab.imlolab.repository.AttachmentRepository;
import uzb.lab.imlolab.service.IAttachmentService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AttachmentService implements IAttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    private final ResultDTO result = new ResultDTO();

    @Override
    public ResponseEntity<ResultDTO> saveAttachment(MultipartFile multipartFile) {
        Attachment attachment = new Attachment();
        attachment.setContentType(multipartFile.getContentType());
        attachment.setFileName(multipartFile.getOriginalFilename().toLowerCase());
        attachment.setFileSize(multipartFile.getSize() / (1024f));
        attachment.setHashId(UUID.randomUUID().toString());
        attachment.setExtension(getExtension(multipartFile.getOriginalFilename().toLowerCase()));
        LocalDate date = LocalDate.now();
        String uploadPath = String.format("%s/%d/%d/%d/%s", uploadFolder, date.getYear(), date.getMonthValue(), date.getDayOfMonth(), attachment.getExtension());
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        attachment.setUploadPath(uploadPath);
        attachment.setLink(String.format("%s/%s.%s", file.getAbsolutePath(),
                /**getFileName(attachment.getFileName()) + "_" +*/
                attachment.getHashId(), attachment.getExtension()));
        attachmentRepository.save(attachment);
        try {
            multipartFile.transferTo(new File(attachment.getLink()));
        } catch (IOException e) {
            log.error("AttachmentService.saveAttachment => {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.success(attachment));
    }

    @Override
    public ResponseEntity<FileUrlResource> preview(String hashId) {
        ResultDTO result = findAttachmentByHashId(hashId);
        if (result.isStatus()) {
            Attachment attachment = (Attachment) result.getData();
            try {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + URLEncoder.encode(attachment.getFileName())).contentType(MediaType.parseMediaType(attachment.getContentType())).body(new FileUrlResource(attachment.getLink()));
            } catch (MalformedURLException e) {
                log.error("AttachmentController.preview.MalformedURLException => {}", e.getMessage());
                throw new RuntimeException("e.getMessage()");
            }
        } else {
            log.error("AttachmentService.preview => {}", result.getMessage());
            throw new RuntimeException(result.getMessage());
        }
    }

    @Override
    public ResponseEntity<FileUrlResource> download(String hashId) {
        ResultDTO result = findAttachmentByHashId(hashId);
        if (result.isStatus()) {
            Attachment attachment = (Attachment) result.getData();
            try {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filaName=" + URLEncoder.encode(attachment.getFileName())).contentType(MediaType.parseMediaType(attachment.getContentType())).body(new FileUrlResource(String.format("%s/%s.%s", attachment.getUploadPath(), attachment.getHashId(), attachment.getExtension())));
            } catch (MalformedURLException e) {
                log.error("AttachmentController.download.MalformedURLException => {}", e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        } else {
            log.error("AttachmentService.download => {}", result.getMessage());
            throw new RuntimeException(result.getMessage());
        }
    }


    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public ResultDTO findAttachmentByHashId(String hashId) {
        Attachment attachment = attachmentRepository.findByHashId(hashId);
        if (attachment == null) return result.error("hashId not found");
        else return result.success(attachment);
    }

    @Override
    public ResponseEntity<ResultDTO> removeAttachmentByHashId(String hashId) {
        try {
            Attachment attachment = attachmentRepository.findByHashId(hashId);
            attachmentRepository.delete(attachment);
            new File(attachment.getLink()).delete();
            return ResponseEntity.status(HttpStatus.OK).body(new ResultDTO().success(result.success(attachment)));
        } catch (Exception e) {
            log.error("AttachmentService.removeAttachmentByHashId => {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultDTO().error(e));
        }
    }


    private String calculateFileHash(MultipartFile file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = file.getBytes();
        byte[] hashBytes = digest.digest(fileBytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
