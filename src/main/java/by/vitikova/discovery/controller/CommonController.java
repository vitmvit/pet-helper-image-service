package by.vitikova.discovery.controller;

import by.vitikova.discovery.model.dto.ImageRequestDto;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommonController {

    @GetMapping("/base64")
    ResponseEntity<String> base64(@RequestParam String uuid);

    @GetMapping("/bytes")
    ResponseEntity<byte[]> bytes(@RequestParam String uuid);

    @GetMapping(value = "/info", produces = "application/json")
    ResponseEntity<ImageResponseDto> findByUuid(@RequestParam String uuid);

    @GetMapping
    ResponseEntity<List<ImageResponseDto>> findAll();

    @PostMapping(value = "/save/img", produces = "application/json")
    ResponseEntity<ImageResponseDto> save(@RequestPart("image") MultipartFile image);

    @PostMapping(value = "/save/dto", consumes = "application/json", produces = "application/json")
    ResponseEntity<ImageResponseDto> save(@RequestBody ImageRequestDto dto);

    @DeleteMapping("/remove")
    void remove(@RequestParam String uuid);
}