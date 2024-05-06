package by.vitikova.discovery.controller.impl;

import by.vitikova.discovery.controller.CommonController;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
public class ImageController implements CommonController {

    private final ImageService imageService;

    @Override
    public ResponseEntity<String> base64(String uuid) {
        return new ResponseEntity<>(imageService.base64(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> bytes(String uuid) {
        return new ResponseEntity<>(imageService.bytes(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> findByUuid(String uuid) {
        var dto = imageService.findByUuid(uuid);
        return dto == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImageResponseDto>> findAll() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ResponseEntity<ImageResponseDto> save(MultipartFile image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> save(ImageRequestDto dto) {
        return new ResponseEntity<>(imageService.save(dto), HttpStatus.OK);
    }

    @Override
    public void remove(String uuid) {
        imageService.remove(uuid);
    }
}
