package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.converter.AvatarConverter;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.repository.AvatarRepository;
import by.vitikova.discovery.service.AvatarService;
import by.vitikova.discovery.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
import java.util.List;

/**
 * Реализация сервиса для работы с аватарками.
 * Класс обеспечивает функционал получения, сохранения и удаления аватарок пользователей.
 */
@AllArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    //fixme: вытянуть из пропертей
    private final String FILE_STORAGE = "/Users/vitmvit/bin/projects/graduation-project/file_storage";
    private final AvatarRepository repository;
    private final FileService fileService;
    private final AvatarConverter converter;

    /**
     * Получает изображение в формате base64 по его уникальному идентификатору.
     *
     * @param uuid уникальный идентификатор изображения
     * @return строка в формате base64, представляющая изображение
     * @throws EntityNotFoundException если изображение не найдено по указанному идентификатору
     */
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

    /**
     * Получает изображение в виде массива байтов по его уникальному идентификатору.
     *
     * @param uuid уникальный идентификатор изображения
     * @return массив байтов, представляющий изображение
     * @throws EntityNotFoundException если изображение не найдено по указанному идентификатору
     */
    @Override
    public byte[] bytes(String uuid) {
        var image = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Image not found by uuid: " + uuid)
        );
        return fileService.bytes(
                FILE_STORAGE + image.getFilePath() + image.getGeneratedName()
        );
    }

    /**
     * Находит изображение по его уникальному идентификатору и преобразует его в DTO.
     *
     * @param uuid уникальный идентификатор изображения
     * @return DTO с информацией об изображении
     * @throws EntityNotFoundException если изображение не найдено по указанному идентификатору
     */
    @Override
    public ImageResponseDto findByUuid(String uuid) {
        return converter.convert(
                repository.findByGeneratedName(uuid).orElseThrow(
                        () -> new EntityNotFoundException("Avatar not found by uuid: " + uuid)
                )
        );
    }

    @Override
    public List<ImageResponseDto> findAll() {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Сохраняет аватар пользователя, представленный в виде MultipartFile.
     *
     * @param multipartFile файл аватара пользователя
     * @return DTO с информацией о сохраненном изображении
     */
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

    /**
     * Удаляет аватар пользователя по его уникальному идентификатору.
     *
     * @param uuid уникальный идентификатор изображения
     * @throws EntityNotFoundException если изображение не найдено по указанному идентификатору
     * @throws RuntimeException        если возникает ошибка при удалении файла
     */
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
