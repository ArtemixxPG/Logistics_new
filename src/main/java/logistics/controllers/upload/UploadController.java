package logistics.controllers.upload;

import logistics.services.uploadFile.UploadCSVService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private UploadCSVService uploadCSVService;

    public UploadController(UploadCSVService uploadCSVService) {
        this.uploadCSVService = uploadCSVService;
    }

    @PostMapping
    @CrossOrigin
    public void upload(@RequestParam("csv") MultipartFile file){
        uploadCSVService.upload(file);
    }
}
