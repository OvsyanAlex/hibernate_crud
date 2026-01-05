package org.example.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static org.example.util.PropertiesUtil.getAllProperties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(getAllProperties())
                .build();

        MetadataSources sources = new MetadataSources(registry)
                .addAnnotatedClass(org.example.entity.WriterEntity.class)
                .addAnnotatedClass(org.example.entity.PostEntity.class)
                .addAnnotatedClass(org.example.entity.LabelEntity.class);

        Metadata metadata = sources.getMetadataBuilder()
                .build();

        sessionFactory = metadata.buildSessionFactory();
    }

    public static <T> T doInTransaction(HibernateTransactionWork<T> work) {
        Session session = getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T result = work.execute(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @FunctionalInterface
    public interface HibernateTransactionWork<T> {
        T execute(Session session);
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}