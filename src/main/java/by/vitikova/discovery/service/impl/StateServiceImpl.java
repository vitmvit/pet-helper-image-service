package by.vitikova.discovery.service.impl;

import by.vitikova.discovery.converter.StateConverter;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.State;
import by.vitikova.discovery.repository.StateRepository;
import by.vitikova.discovery.service.FileService;
import by.vitikova.discovery.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
import java.util.List;

@AllArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    //fixme: вытянуть из пропертей
    private static final String FILE_STORAGE = "/Users/vitmvit/bin/projects/graduation-project/file_storage";
    private final StateRepository repository;
    private final FileService fileService;
    private final StateConverter converter;

    @Override
    public String base64(String uuid) {
        var state = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("State not found by uuid: " + uuid)
        );
        return fileService.base64(
                FILE_STORAGE + state.getFilePath() + state.getGeneratedName(),
                state.getMimeType()
        );
    }

    @Override
    public byte[] bytes(String uuid) {
        var state = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("State not found by uuid: " + uuid)
        );
        return fileService.bytes(
                FILE_STORAGE + state.getFilePath() + state.getGeneratedName()
        );
    }

    @Override
    public ImageResponseDto findByUuid(String uuid) {
        return converter.convert(repository.findByGeneratedName(uuid).orElse(null));
    }

    public List<ImageResponseDto> findAll() {
        List<State> stateList = repository.findAll();
        return stateList.isEmpty()
                ? List.of()
                : stateList.stream().map(converter::convert).toList();
    }

    @Override
    public ImageResponseDto save(MultipartFile multipartFile) {
        var imageResponseDto = fileService.save(multipartFile);
        repository.save(converter.convert(imageResponseDto));
        return imageResponseDto;
    }

    @Override
    public ImageResponseDto save(ImageRequestDto dto) {
        var stateOptional = repository.findByGeneratedName(dto.getUuid());
        if (stateOptional.isPresent()) {
            var state = stateOptional.get();
            return converter.convert(repository.save(state));
        }
        throw new EntityNotFoundException("State not found by uuid: " + dto.getUuid());
    }

    @Override
    public void remove(String uuid) {
        var state = repository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("State not found by uuid: " + uuid)
        );
        try {
            fileService.remove(state.getFilePath() + state.getGeneratedName());
            repository.deleteById(uuid);
        } catch (FileSystemException ex) {
            throw new RuntimeException(ex);
        }
    }
}
