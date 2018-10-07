package com.hiberus.music_collection.repository;

import com.hiberus.music_collection.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class PersonDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void create(Person person) {
        entityManager.persist(person);
    }


    public void update(Person person) {
        entityManager.merge(person);
    }


    public Person getPersonById(long id) {
        return entityManager.find(Person.class, id);
    }


    public void delete(long id) {
        Person person = getPersonById(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    public Boolean exists(String name) {
        TypedQuery<Person> lQuery = entityManager.createQuery("select p from person p where upper(p.name) like upper('" + name + "')", Person.class);
        if(lQuery.getResultList().size() != 0) {
            return true;
        } else {
            return false;
        }
    }
}