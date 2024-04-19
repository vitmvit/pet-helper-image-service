package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.exception.SaveImageException;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.service.FileService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final int MIN = 97;
    private static final int MAX = 122;

    @Value("${app.file-storage.path}")
    private String fileStorage;

    @Value("${app.file-storage.deep}")
    private int fileDeep;

    private Random random;

    @PostConstruct
    public void init() {
        random = new Random();
    }

    @Override
    public ImageResponseDto save(MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        String filePath = filePath();
        String fullPath = fileStorage + filePath;

        File dir = new File(fullPath);
        dir.mkdirs();

        File file = new File(fullPath + uuid);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new SaveImageException("Can not create file for saving: " + fullPath + uuid, ex);
        }

        try (OutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
            return ImageResponseDto
                    .builder()
                    .generatedName(uuid)
                    .originalName(multipartFile.getOriginalFilename())
                    .mimeType(multipartFile.getContentType())
                    .filePath(filePath)
                    .build();
        } catch (IOException ex) {
            throw new SaveImageException("Can't save image: " + multipartFile.getOriginalFilename(), ex);
        }
    }

    @Override
    public String base64(String filePath, String mimeType) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            var encodedString = Base64.encodeBase64String(fileContent);
            return "data:" + mimeType + ";base64, " + encodedString;
        } catch (IOException ex) {
            // todo: норм исключение
            throw new RuntimeException("");
        }
    }

    @Override
    public Resource resource(String filePath) {
        File file = new File(filePath);
        try {
            Path pathFile = Paths.get(file.getAbsolutePath().replace(file.getName(), ""));
            Path pathResource = pathFile.resolve(file.getName());
            return new UrlResource(pathResource.toUri());
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Cannot get resource from file: " + file.getAbsolutePath());
        }
    }

    @Override
    public byte[] bytes(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException ex) {
            // todo: kaa: jhasgdjhgasd
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(String filePath) throws FileSystemException {
        File file = new File(fileStorage + filePath);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new FileSystemException("Can not remove file: " + filePath);
            }
        }
    }

    private String filePath() {
        StringBuilder path = new StringBuilder("/");
        for (int i = 0; i < fileDeep; i++) {
            path.append(randomLetter()).append("/");
        }
        return path.toString();
    }

    private char randomLetter() {
        return (char) (random.nextInt(MAX - MIN + 1) + MIN);
    }
}
