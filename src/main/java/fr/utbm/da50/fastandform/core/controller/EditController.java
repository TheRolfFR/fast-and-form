package fr.utbm.da50.fastandform.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.utbm.da50.fastandform.core.service.EntityService;
import fr.utbm.da50.fastandform.core.service.VerifyService;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// role : post controller -> appeler service des entités qui va 
// récupérer le body de la requete du json en string ()

@RestController
// @RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = {
//         RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
class EditController {

    @Autowired
    private VerifyService verifservice;
    @Autowired
    private EntityService entityservice;

    @GetMapping(value="/hello2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String resultctr() {
      return "Hello world 2";
    }

    @PostMapping(value="/hello2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody JsonNode jsonNode) {

        String data = jsonNode.toString();

        if (Boolean.TRUE.equals(verifservice.verif(data))) {
             entityservice.add(data);
             data = "verif + add + " + data;
        }

        return "hello" + data;
    }

    @PutMapping(value="/hello2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody JsonNode jsonNode) {

        String data = jsonNode.toString();

        if (Boolean.TRUE.equals(verifservice.verif(data))) {
            entityservice.update(data);
        }

        return data;
    }

    @DeleteMapping(value="/hello2/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){

        Boolean isRemoved = false;
        isRemoved = entityservice.delete(id);

        if (Boolean.FALSE.equals(isRemoved)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    
}