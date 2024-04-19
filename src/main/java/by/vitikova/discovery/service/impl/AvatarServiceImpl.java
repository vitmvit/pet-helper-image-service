package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.converter.AvatarConverter;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.repository.AvatarRepository;
import by.vitikova.discovery.service.FileService;
import by.vitikova.discovery.service.AvatarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

@AllArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    //fixme: вытянуть из пропертей
    private final String FILE_STORAGE = "/Users/vitmvit/bin/projects/graduation-project/file_storage";
    private final AvatarRepository repository;
    private final FileService fileService;
    private final AvatarConverter converter;

    @Override
    public String base64(String uuid) {
        var avatar = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Avatar not found by uuid: " + uuid)
        );
        return fileService.base64(
                FILE_STORAGE + avatar.getFilePath() + avatar.getGeneratedName(),
                avatar.getMimeType()
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
        return converter.convert(
                repository.findByGeneratedName(uuid).orElseThrow(
                        () -> new EntityNotFoundException("Avatar not found by uuid: " + uuid)
                )
        );
    }

    @Override
    public ImageResponseDto save(MultipartFile multipartFile) {
        var imageResponseDto = fileService.save(multipartFile);
        repository.save(converter.convert(imageResponseDto));
        return imageResponseDto;
    }

    @Override
    public ImageResponseDto save(ImageRequestDto dto) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void remove(String uuid) {
        var image = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Avatar not found by uuid: " + uuid)
        );
        try {
            fileService.remove(image.getFilePath() + image.getGeneratedName());
            repository.deleteById(uuid);
        } catch (FileSystemException ex) {
            throw new RuntimeException(ex);
        }
    }
}
