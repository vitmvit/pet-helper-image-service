package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.converter.ImageConverter;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.repository.ImageRepository;
import by.vitikova.discovery.service.FileService;
import by.vitikova.discovery.service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    //fixme: вытянуть из пропертей
    private static final String FILE_STORAGE = "/Users/vitmvit/bin/projects/graduation-project/file_storage";
    private final ImageRepository repository;
    private final FileService fileService;
    private final ImageConverter converter;

    @Override
    public String base64(String uuid) {
        var image = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Image not found by uuid: " + uuid)
        );
        return fileService.base64(
                FILE_STORAGE + image.getFilePath() + image.getGeneratedName(),
                image.getMimeType()
        );
    }

    @Override
    public byte[] bytes(String uuid) {
        var image = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Image not found by uuid: " + uuid)
        );
        return fileService.bytes(
                FILE_STORAGE + image.getFilePath() + image.getGeneratedName()
        );
    }

    @Override
    public ImageResponseDto findByUuid(String uuid) {
        return converter.convert(repository.findByGeneratedName(uuid).orElse(null));
    }

    @Override
    public ImageResponseDto save(MultipartFile multipartFile) {
        var imageResponseDto = fileService.save(multipartFile);
        repository.save(converter.convert(imageResponseDto));
        return imageResponseDto;
    }

    @Override
    public ImageResponseDto save(ImageRequestDto dto) {
        var imageOptional = repository.findByGeneratedName(dto.getUuid());
        if (imageOptional.isPresent()) {
            var image = imageOptional.get();
            image.setDescription(dto.getDescription());
            return converter.convert(repository.save(image));
        }
        throw new EntityNotFoundException("Image not found by uuid: " + dto.getUuid());
    }

    @Override
    public void remove(String uuid) {
        var image = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Image not found by uuid: " + uuid)
        );
        try {
            fileService.remove(image.getFilePath() + image.getGeneratedName());
            repository.deleteById(uuid);
        } catch (FileSystemException ex) {
            throw new RuntimeException(ex);
        }
    }
}
