package org.example.mapper;

import org.example.entity.LabelEntity;
import org.example.entity.PostEntity;
import org.example.entity.WriterEntity;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Writer;

import java.util.ArrayList;
import java.util.List;


public class WriterMapper {
    public static Writer toModel(WriterEntity writerEntity) {

        if (writerEntity == null) {
            return null;
        }
        Writer writer = new Writer(
                writerEntity.getId(),
                writerEntity.getFirstName(),
                writerEntity.getLastName(),
                writerEntity.getStatus(),
                new ArrayList<>()
        );
        List<Post> posts = new ArrayList<>();

        for (PostEntity postEntity : writerEntity.getPosts()) {
            List<Label> labels = new ArrayList<>();
            for (LabelEntity labelEntity : postEntity.getLabels()) {
                labels.add(
                        new Label(
                                labelEntity.getId(),
                                labelEntity.getName(),
                                labelEntity.getStatus()
                        )
                );
            }
            Post post = new Post(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getStatus(),
                    labels
            );
            posts.add(post);
        }
        writer.setPosts(posts);
        return writer;
    }

    public static WriterEntity toEntity(Writer writer) {
        WriterEntity entity = null;
        if (writer != null) {
            entity = new WriterEntity(
                    writer.getId(),
                    writer.getFirstName(),
                    writer.getLastName(),
                    writer.getStatus());
        }
        return entity;
    }
}
