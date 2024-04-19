package by.vitikova.discovery.converter.impl;

import by.vitikova.discovery.converter.AvatarConverter;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.Avatar;
import org.springframework.stereotype.Component;

@Component
public class AvatarConverterImpl implements AvatarConverter {

    @Override
    public ImageResponseDto convert(Avatar source) {
        if (source == null) {
            return null;
        }
        ImageResponseDto target = new ImageResponseDto();
        target.setGeneratedName(source.getGeneratedName());
        target.setOriginalName(source.getOriginalName());
        target.setMimeType(source.getMimeType());
        target.setFilePath(source.getFilePath());
        return target;
    }

    @Override
    public Avatar convert(ImageResponseDto source) {
        if (source == null) {
            return null;
        }
        Avatar target = new Avatar();
        target.setGeneratedName(source.getGeneratedName());
        target.setOriginalName(source.getOriginalName());
        target.setMimeType(source.getMimeType());
        target.setFilePath(source.getFilePath());
        return target;
    }
}
