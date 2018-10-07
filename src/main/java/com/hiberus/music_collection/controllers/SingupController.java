package com.hiberus.music_collection.controllers;

import com.hiberus.music_collection.domain.Authority;
import com.hiberus.music_collection.domain.Person;
import com.hiberus.music_collection.repository.AuthorityDao;
import com.hiberus.music_collection.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/singup")
public class SingupController {

    @Autowired
    PersonService personService;

    @Autowired
    AuthorityDao authorityDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignUp(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            // Usuario logueado
            return "redirect:/artist";
        }
        model.addAttribute("exists", null);
        return "singup";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"singup"})
    public String registro(Model model, HttpServletRequest request,
                           @RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                           @RequestParam(value = "years") Integer years) {

        // Comprobar si existe ya la persona
        if(!personService.exists(name)) {
            Person person = new Person();
            person.setName(name);
            person.setYear(years);
            person.setPassword(passwordEncoder.encode(password));
            person.setEnabled(true);
            personService.create(person);

            Authority authority = new Authority();
            authority.setName(name);
            authority.setAuthority("USER");
            authorityDao.create(authority);

            return "redirect:/login";

        } else {
            model.addAttribute("exists", true);
            return "singup";
        }

    }

}
