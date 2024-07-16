package by.vitikova.discovery.converter.impl;

import by.vitikova.discovery.converter.StateConverter;
import by.vitikova.discovery.model.dto.ImageResponseDto;
import by.vitikova.discovery.model.entity.State;
import org.springframework.stereotype.Component;

@Component
public class StateConverterImpl implements StateConverter {

    @Override
    public ImageResponseDto convert(State source) {
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
    public State convert(ImageResponseDto source) {
        if (source == null) {
            return null;
        }
        State target = new State();
        target.setGeneratedName(source.getGeneratedName());
        target.setOriginalName(source.getOriginalName());
        target.setMimeType(source.getMimeType());
        target.setFilePath(source.getFilePath());
        return target;
    }
}