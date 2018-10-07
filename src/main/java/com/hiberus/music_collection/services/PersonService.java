package com.hiberus.music_collection.services;


import com.hiberus.music_collection.domain.Authority;
import com.hiberus.music_collection.domain.Person;
import com.hiberus.music_collection.repository.AuthorityDao;
import com.hiberus.music_collection.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    AuthorityDao authorityDao;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public boolean register (String name, String password, Integer years) {
        // Comprobar si existe ya la persona
        if(!exists(name)) {
            Person person = new Person();
            person.setName(name);
            person.setYear(years);
            person.setPassword(passwordEncoder.encode(password));
            person.setEnabled(true);
            personDao.create(person);

            Authority authority = new Authority();
            authority.setName(name);
            authority.setAuthority("USER");
            authorityDao.create(authority);

            return true;

        } else {
            return false;
        }
    }

    public boolean exists (String name) {
        return personDao.exists(name);
    }

}