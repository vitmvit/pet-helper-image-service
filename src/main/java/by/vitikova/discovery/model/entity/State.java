package by.vitikova.discovery.model.entity;

import by.vitikova.discovery.model.entity.parent.ParentEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("s")
public class State extends ParentEntity {
}