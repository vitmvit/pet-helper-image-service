package by.vitikova.discovery.service;

import by.vitikova.discovery.model.dto.ImageResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

public interface FileService {

    ImageResponseDto save(MultipartFile multipartFile);

    String base64(String filePath, String mimeType);

    Resource resource(String filePath);

    byte[] bytes(String filePath);

    void remove(String filePath) throws FileSystemException;
}