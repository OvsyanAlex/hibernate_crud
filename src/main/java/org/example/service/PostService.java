package org.example.service;

import org.example.entity.PostEntity;
import org.example.mapper.PostMapper;
import org.example.model.Post;
import org.example.repository.PostRepositoryImpl;
import org.example.repository.WriterRepositoryImpl;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PostService {
    PostRepositoryImpl postRepository;
    WriterRepositoryImpl writerRepository;

    public PostService(PostRepositoryImpl postRepository, WriterRepositoryImpl writerRepository) {
        this.postRepository = postRepository;
        this.writerRepository = writerRepository;
    }

    public Post save(Post post) {
        PostEntity newPostEntity = postRepository.save(PostMapper.toEntity(post));
        writerRepository.addAndSavePost(newPostEntity, post.getWriterId());
        return PostMapper.toModel(newPostEntity);
    }

    public Post update(Post post) {
        PostEntity update = postRepository.update(PostMapper.toEntity(post));
        return PostMapper.toModel(update);
    }

    public Post getById(Long id) {
        PostEntity postEntity = postRepository.getById(id);
        return PostMapper.toModel(postEntity);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream().map(PostMapper::toModel).toList();
    }
}
