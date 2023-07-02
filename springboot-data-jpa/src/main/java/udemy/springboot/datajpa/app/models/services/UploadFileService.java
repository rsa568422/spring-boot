package udemy.springboot.datajpa.app.models.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UploadFileService {

    Resource load(String fileName) throws MalformedURLException;

    String copy(MultipartFile file) throws IOException;

    boolean delete(String fileName);
}
