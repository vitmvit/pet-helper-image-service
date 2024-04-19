package by.vitikova.discovery.converter.impl;

import by.vitikova.discovery.converter.ImageConverter;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterImpl implements ImageConverter {

    @Override
    public ImageResponseDto convert(Image source) {
        if (source == null) {
            return null;
        }
        ImageResponseDto target = new ImageResponseDto();
        target.setGeneratedName(source.getGeneratedName());
        target.setOriginalName(source.getOriginalName());
        target.setMimeType(source.getMimeType());
        target.setFilePath(source.getFilePath());
        target.setDescription(source.getDescription());
        return target;
    }

    @Override
    public Image convert(ImageResponseDto source) {
        if (source == null) {
            return null;
        }
        Image target = new Image();
        target.setGeneratedName(source.getGeneratedName());
        target.setOriginalName(source.getOriginalName());
        target.setMimeType(source.getMimeType());
        target.setFilePath(source.getFilePath());
        target.setDescription(source.getDescription());
        return target;
    }
}
