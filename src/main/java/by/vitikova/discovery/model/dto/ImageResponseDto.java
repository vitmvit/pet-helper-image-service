package by.vitikova.discovery.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageResponseDto {

    private String generatedName;
    private String originalName;
    private String mimeType;
    private String filePath;
    private String description;
}