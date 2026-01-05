package org.example.mapper;

import org.example.entity.LabelEntity;
import org.example.model.Label;


public class LabelMapper {
    public static Label toModel(LabelEntity entity) {
        Label label = null;
        if (entity != null) {
            label = new Label(
                    entity.getId(),
                    entity.getName(),
                    entity.getStatus());
        }
        return label;
    }

    public static LabelEntity toEntity(Label label) {
        LabelEntity entity = null;
        if (label != null) {
            entity = new LabelEntity(
                    label.getId(),
                    label.getName(),
                    label.getStatus());
        }
        return entity;
    }
}
