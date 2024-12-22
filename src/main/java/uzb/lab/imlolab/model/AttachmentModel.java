package uzb.lab.imlolab.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentModel {

    Long id;
    String fileName;
    String hashId;
    String contentType;
    String extension;
    String uploadPath;
    String link;
    Float fileSize;

    @Override
    public String toString() {
        return "AttachmentModel{" + ",\n" +
                "   id=" + id + ",\n" +
                "   fileName='" + fileName + '\'' + ",\n" +
                "   hashId='" + hashId + '\'' + ",\n" +
                "   contentType='" + contentType + '\'' + ",\n" +
                "   extension='" + extension + '\'' + ",\n" +
                "   uploadPath='" + uploadPath + '\'' + ",\n" +
                "   link='" + link + '\'' + ",\n" +
                "   fileSize=" + fileSize + ",\n" +
                '}';
    }


}
