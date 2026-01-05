package org.example.repository;

import org.example.entity.LabelEntity;
import org.example.entity.PostEntity;
import org.example.model.Label;
import org.example.model.Status;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;


import java.util.*;



public class LabelRepositoryImpl implements LabelRepository {

    @Override
    public LabelEntity save(LabelEntity labelEntity) {
        HibernateUtil.doInTransaction(session -> session.save(labelEntity));
        return labelEntity;
    }

    @Override
    public LabelEntity update(LabelEntity labelEntity) {
        return HibernateUtil.doInTransaction(session -> {
            LabelEntity persistent = session.merge(labelEntity);
            return persistent;
        });
    }

    @Override
    public void deleteById(Long id) {
        HibernateUtil.doInTransaction(session -> {
            LabelEntity labelEntity = session.get(LabelEntity.class, id);
            labelEntity.setStatus(Status.DELETED);
            session.merge(labelEntity);
            return null;
        });
    }

    @Override
    public LabelEntity getById(Long id) {
        LabelEntity labelEntity = HibernateUtil.doInTransaction(session ->session.get(LabelEntity.class, id));
        return labelEntity;
    }

    @Override
    public List<LabelEntity> findAll() {
        return HibernateUtil.doInTransaction(session -> {
            List<LabelEntity> labelEntities = session.createQuery(
                    "SELECT DISTINCT l FROM LabelEntity l",
                    LabelEntity.class
            ).getResultList();
            return labelEntities;
        });
    }
}
