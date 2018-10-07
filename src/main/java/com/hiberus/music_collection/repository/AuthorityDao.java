package com.hiberus.music_collection.repository;

import com.hiberus.music_collection.domain.Authority;
import com.hiberus.music_collection.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AuthorityDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void create(Authority authority) {
        entityManager.persist(authority);
    }

    public void update(Authority authority) {
        entityManager.merge(authority);
    }

}