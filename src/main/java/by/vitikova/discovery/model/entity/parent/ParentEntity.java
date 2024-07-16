package by.vitikova.discovery.model.entity.parent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "img_type")
public class ParentEntity {

    @Id
    @Column(length = 40, unique = true)
    private String generatedName;

    private String originalName;

    private String mimeType;

    private String filePath;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreation;
}