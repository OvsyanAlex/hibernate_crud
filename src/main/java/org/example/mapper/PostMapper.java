package org.example.mapper;

import org.example.entity.PostEntity;
import org.example.model.Label;
import org.example.model.Post;

import java.util.List;
import java.util.stream.Collectors;


public class PostMapper {
    public static Post toModel(PostEntity postEntity) {
        List<Label> labels = postEntity.getLabels().stream()
                .map(label -> new Label(label.getId(), label.getName(), label.getStatus()))
                .collect(Collectors.toList());

        return new Post(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getStatus(),
                labels
        );
    }

    public static PostEntity toEntity(Post post) {
        PostEntity entity = null;
        if (post != null) {
            entity = new PostEntity(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getStatus());
        }
        return entity;
    }
}
