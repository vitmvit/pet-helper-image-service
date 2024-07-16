package by.vitikova.discovery.controller.impl;

import by.vitikova.discovery.controller.CommonController;
import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.service.impl.StateServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/stateImages")
public class StateController implements CommonController {

    private final StateServiceImpl stateService;

    @Override
    public ResponseEntity<String> base64(String uuid) {
        return new ResponseEntity<>(stateService.base64(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> bytes(String uuid) {
        return new ResponseEntity<>(stateService.bytes(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImageResponseDto>> findAll() {
        return new ResponseEntity<>(stateService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> findByUuid(String uuid) {
        var dto = stateService.findByUuid(uuid);
        return dto == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> save(MultipartFile image) {
        return new ResponseEntity<>(stateService.save(image), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponseDto> save(ImageRequestDto dto) {
        return new ResponseEntity<>(stateService.save(dto), HttpStatus.OK);
    }

    @Override
    public void remove(String uuid) {
        stateService.remove(uuid);
    }
}