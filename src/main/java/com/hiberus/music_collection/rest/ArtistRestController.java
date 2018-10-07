package com.hiberus.music_collection.rest;

import com.hiberus.music_collection.services.ArtistService;
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
@RequestMapping("/api/artist")
@Api(value = "Artist endpoint", description = "Operations pertaining to artist in Music Collection")
public class ArtistRestController {

    @Autowired
    ArtistService artistService;

    /*

      {
	    "name" : "Julio Iglesias",
	    "year" : 1980,
	    "style" : "latin"
      }

     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new artist", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created artist"),
            @ApiResponse(code = 500, message = "Some error has ocurred while creating artist")
    })
    public ResponseEntity<String> createArtist(@ApiParam(value = "person parameters (name, year, style) in JSON", required = true) @RequestBody String jsonRequest) {
        String name;
        Integer year;
        String style;

        try {

            JSONObject jsonObject = new JSONObject(jsonRequest);
            name = jsonObject.getString("name");
            year = jsonObject.getInt("year");
            style = jsonObject.getString("style");

            artistService.createArtist(name, year, style);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
