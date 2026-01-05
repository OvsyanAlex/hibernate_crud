package org.example.repository;

import org.example.entity.PostEntity;
import org.example.entity.WriterEntity;
import org.example.model.Status;
import org.example.model.Writer;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;

import java.util.*;

import static org.example.util.HibernateUtil.getCurrentSession;

public class WriterRepositoryImpl implements WriterRepository {

    @Override
    public WriterEntity save(WriterEntity writerEntity) {
        HibernateUtil.doInTransaction(session -> session.save(writerEntity));
        return writerEntity;
    }

    @Override
    public WriterEntity update(WriterEntity writerEntity) {
        return HibernateUtil.doInTransaction(session -> {
            WriterEntity persistent = session.merge(writerEntity);
            return persistent;
        });
    }

    @Override
    public void deleteById(Long id) {
        HibernateUtil.doInTransaction(session -> {
            WriterEntity writerEntity = session.get(WriterEntity.class, id);
            writerEntity.setStatus(Status.DELETED);
            session.merge(writerEntity);
            return null;
        });
    }


    @Override
    public WriterEntity getById(Long id) {
        return HibernateUtil.doInTransaction(session ->
                session.createQuery(
                                "SELECT DISTINCT w " +
                                "FROM WriterEntity w " +
                                "LEFT JOIN FETCH w.posts p " +
                                "LEFT JOIN FETCH p.labels " +
                                "WHERE w.id = :id", WriterEntity.class)
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    @Override
    public List<WriterEntity> findAll() {
        return HibernateUtil.doInTransaction(session -> {

            List<WriterEntity> writers = session.createQuery(
                    "SELECT DISTINCT w FROM WriterEntity w",
                    WriterEntity.class
            ).getResultList();

            writers.forEach(writer -> {
                Hibernate.initialize(writer.getPosts());
                writer.getPosts().forEach(post -> Hibernate.initialize(post.getLabels()));
            });
            return writers;
        });
    }

    public void addAndSavePost(PostEntity postEntity, Long writerId) {
        WriterEntity writerEntity = getById(writerId);
        writerEntity.getPosts().add(postEntity);
        HibernateUtil.doInTransaction(session -> session.merge(writerEntity));
    }
}
