package by.vitikova.discovery.service;

import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    String base64(String uuid);

    byte[] bytes(String uuid);

    ImageResponseDto findByUuid(String uuid);

    ImageResponseDto save(MultipartFile multipartFile);

    ImageResponseDto save(ImageRequestDto dto);

    void remove(String uuid);
}
