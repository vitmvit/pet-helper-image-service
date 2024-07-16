package by.vitikova.discovery.converter;

import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.Image;

public interface ImageConverter {

    ImageResponseDto convert(Image source);

    Image convert(ImageResponseDto source);
}