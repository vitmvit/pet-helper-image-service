package by.vitikova.discovery.controller;

import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface CommonController {

    @GetMapping("/base64")
    ResponseEntity<String> base64(@RequestParam String uuid);

    @GetMapping("/bytes")
    ResponseEntity<byte[]> bytes(@RequestParam String uuid);

    @GetMapping(value = "/info", produces = "application/json")
    ResponseEntity<ImageResponseDto> findByUuid(@RequestParam String uuid);

    @PostMapping(value = "/save/img", produces = "application/json")
    ResponseEntity<ImageResponseDto> save(@RequestPart("image") MultipartFile image);

    @PostMapping(value = "/save/dto", consumes = "application/json", produces = "application/json")
    ResponseEntity<ImageResponseDto> save(@RequestBody ImageRequestDto dto);

    @DeleteMapping("/remove")
    void remove(@RequestParam String uuid);
}