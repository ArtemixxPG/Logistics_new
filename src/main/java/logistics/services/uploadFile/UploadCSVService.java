package logistics.services.uploadFile;

import org.springframework.web.multipart.MultipartFile;

public interface UploadCSVService {
    void upload(MultipartFile multipartFile);
}
