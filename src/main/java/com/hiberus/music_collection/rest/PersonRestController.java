package com.hiberus.music_collection.rest;


import com.hiberus.music_collection.services.PersonService;
import io.swagger.annotations.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
@Api(value = "Person endpoint", description = "Operations pertaining to people in Music Collection")
public class PersonRestController {

    @Autowired
    PersonService personService;


    /*

      {
        "name" : "patatas91",
        "password" : "12345",
        "year" : 1991
      }

     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new person", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created person"),
            @ApiResponse(code = 500, message = "Some error has ocurred while creating person")
    })
    public ResponseEntity<String> createPerson(@ApiParam(value = "person parameters (name, password, year) in JSON", required = true) @RequestBody String jsonRequest) {
        String name;
        Integer year;
        String password;

        try {

            JSONObject jsonObject = new JSONObject(jsonRequest);
            name = jsonObject.getString("name");
            year = jsonObject.getInt("year");
            password = jsonObject.getString("password");

            personService.createPerson(name, year, password);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

