package by.vitikova.discovery.converter;

import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.State;

public interface StateConverter {

    ImageResponseDto convert(State source);

    State convert(ImageResponseDto source);
}
