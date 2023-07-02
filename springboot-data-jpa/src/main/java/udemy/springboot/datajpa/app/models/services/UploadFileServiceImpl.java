package udemy.springboot.datajpa.app.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import udemy.springboot.datajpa.app.controllers.CustomerController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private static final String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path path = getPath(fileName);
        log.info("photoPath: {}", path);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("No se puede cargar la imagen");
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = String.format("%s_%s", UUID.randomUUID(), file.getOriginalFilename());
        Path rootPath = getPath(fileName);
        log.info("rootPath: {}", rootPath);
        Files.copy(file.getInputStream(), rootPath);
        return fileName;
    }

    @Override
    public boolean delete(String fileName) {
        File file = getPath(fileName).toFile();
        return file.exists() && file.canRead() && file.delete();
    }

    public Path getPath(String fileName) {
        return Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
    }
}
