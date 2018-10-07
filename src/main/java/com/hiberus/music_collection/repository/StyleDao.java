package com.hiberus.music_collection.repository;


import com.hiberus.music_collection.domain.Style;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StyleDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void create(Style style) {
        entityManager.persist(style);
    }


    public void update(Style style) {
        entityManager.merge(style);
    }


    public Style getStyleById(long id) {
        return entityManager.find(Style.class, id);
    }

    public Style getStyleByName(String name) {
        TypedQuery<Style> lQuery = entityManager.createQuery("select s from style s where upper(s.name) like upper('" + name + "')", Style.class);
        return lQuery.getResultList().get(0);
    }

    public List<Style> getAllStyle() {
        return entityManager.createQuery("select name from style").getResultList();
    }

    public void delete(long id) {
        Style style = getStyleById(id);
        if (style != null) {
            entityManager.remove(style);
        }
    }
}