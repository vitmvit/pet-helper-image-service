package by.vitikova.discovery.controller.impl;

import by.vitikova.discovery.controller.CommonController;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.service.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/avatars")
public class AvatarController implements CommonController {

    private final AvatarService avatarService;

    @Override
    public ResponseEntity<String> base64(String uuid) {
        return new ResponseEntity<>(avatarService.base64(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> bytes(String uuid) {
        return new ResponseEntity<>(avatarService.bytes(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> findByUuid(String uuid) {
        var dto = avatarService.findByUuid(uuid);
        return dto == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(avatarService.findByUuid(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImageResponseDto>> findAll() {
        // TODO: 02.05.2024  
        return null;
    }

    @Override
    public ResponseEntity<ImageResponseDto> save(MultipartFile file) {
        return new ResponseEntity<>(avatarService.save(file), HttpStatus.OK);
    }

    // обновление аватара, не реализовывать
    @Override
    public ResponseEntity<ImageResponseDto> save(ImageRequestDto dto) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void remove(String uuid) {
        avatarService.remove(uuid);
    }
}
