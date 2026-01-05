package org.example.repository;

import org.example.entity.LabelEntity;
import org.example.entity.PostEntity;
import org.example.entity.WriterEntity;
import org.example.model.Status;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;

import java.util.List;

import static org.example.util.HibernateUtil.getCurrentSession;


public class PostRepositoryImpl implements PostRepository {

    @Override
    public PostEntity save(PostEntity postEntity) {
        HibernateUtil.doInTransaction(session -> session.save(postEntity));
        return postEntity;
    }

    @Override
    public PostEntity update(PostEntity postEntity) {
        return HibernateUtil.doInTransaction(session -> {
            PostEntity persistent = session.merge(postEntity);
            return persistent;
        });
    }

    @Override
    public void deleteById(Long id) {
        HibernateUtil.doInTransaction(session -> {
            PostEntity postEntity = session.get(PostEntity.class, id);
            postEntity.setStatus(Status.DELETED);
            session.merge(postEntity);
            return null;
        });
    }


    @Override
    public PostEntity getById(Long id) {
        return HibernateUtil.doInTransaction(session ->
                session.createQuery(
                                "SELECT  p " +
                                "FROM PostEntity p " +
                                "LEFT JOIN FETCH p.labels " +
                                "WHERE p.id = :id", PostEntity.class)
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    @Override
    public List<PostEntity> findAll() {
        return HibernateUtil.doInTransaction(session -> {

            List<PostEntity> posts = session.createQuery(
                    "SELECT p FROM PostEntity p",
                    PostEntity.class
            ).getResultList();

            posts.forEach(post -> {
                Hibernate.initialize(post.getLabels());
            });
            return posts;
        });
    }

    public void addAndSaveLabel(LabelEntity newLabelEntity, Long postId) {
        PostEntity postEntity = getById(postId);
        postEntity.getLabels().add(newLabelEntity);
        HibernateUtil.doInTransaction(session -> session.merge(postEntity));
    }
}
