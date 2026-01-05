package org.example.service;

import org.example.entity.LabelEntity;
import org.example.entity.PostEntity;
import org.example.mapper.LabelMapper;
import org.example.mapper.PostMapper;
import org.example.model.Label;
import org.example.repository.LabelRepositoryImpl;
import org.example.repository.PostRepositoryImpl;

import java.util.List;

public class LabelService {
    LabelRepositoryImpl labelRepository;
    PostRepositoryImpl postRepository;

    public LabelService(LabelRepositoryImpl labelRepository, PostRepositoryImpl postRepository) {
        this.labelRepository = labelRepository;
        this.postRepository = postRepository;
    }

    public Label save(Label label) {
        LabelEntity newLabelEntity = labelRepository.save(LabelMapper.toEntity(label));
        postRepository.addAndSaveLabel(newLabelEntity, label.getPostId());
        return LabelMapper.toModel(newLabelEntity);
    }

    public Label update(Label label) {
        LabelEntity update = labelRepository.update(LabelMapper.toEntity(label));
        return LabelMapper.toModel(update);
    }

    public Label getById(Long id) {
        LabelEntity labelEntity = labelRepository.getById(id);
        return LabelMapper.toModel(labelEntity);
    }

    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }

    public List<Label> findAll() {
        List<LabelEntity> labelEntities = labelRepository.findAll();
        return labelEntities.stream().map(LabelMapper::toModel).toList();
    }
}
