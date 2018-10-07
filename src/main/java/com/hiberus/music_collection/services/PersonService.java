package com.hiberus.music_collection.services;


import com.hiberus.music_collection.domain.Authority;
import com.hiberus.music_collection.domain.Person;
import com.hiberus.music_collection.repository.AuthorityDao;
import com.hiberus.music_collection.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    AuthorityDao authorityDao;

    public void create(Person person) {
        personDao.create(person);
    }

    public void createPerson(String name, Integer year, String password) {
        Person person = new Person();
        person.setName(name);
        person.setYear(year);
        person.setPassword(password);
        person.setEnabled(true);
        personDao.create(person);

        Authority authority = new Authority();
        authority.setName(name);
        authority.setAuthority("USER");
        authorityDao.create(authority);
    }

    public boolean exists (String name) {
        return personDao.exists(name);
    }

}