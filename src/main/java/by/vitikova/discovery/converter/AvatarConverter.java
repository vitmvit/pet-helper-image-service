package by.vitikova.discovery.converter;

import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.Avatar;

public interface AvatarConverter {

    ImageResponseDto convert(Avatar source);

    Avatar convert(ImageResponseDto source);
}
