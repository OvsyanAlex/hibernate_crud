package org.example.service;

import org.example.entity.WriterEntity;
import org.example.mapper.WriterMapper;
import org.example.model.Writer;
import org.example.repository.WriterRepositoryImpl;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WriterService {
    WriterRepositoryImpl writerRepository;

    public WriterService(WriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer save(Writer writer) {
        WriterEntity save = writerRepository.save(WriterMapper.toEntity(writer));
        return WriterMapper.toModel(save);
    }

    public Writer update(Writer writer) {
        WriterEntity update = writerRepository.update(WriterMapper.toEntity(writer));
        return WriterMapper.toModel(update);
    }

    public Writer getById(Long id) {
        WriterEntity writerEntity = writerRepository.getById(id);
        return WriterMapper.toModel(writerEntity);
    }

    public void deleteById(long id) {
        writerRepository.deleteById(id);
    }

    public List<Writer> findAll() {
        List<WriterEntity> writerEntities = writerRepository.findAll();
        return writerEntities.stream().map(WriterMapper::toModel).toList();
    }
}
