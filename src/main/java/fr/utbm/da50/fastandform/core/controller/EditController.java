package fr.utbm.da50.fastandform.core.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.utbm.da50.fastandform.core.repository.WriteRepository;
import fr.utbm.da50.fastandform.core.service.EntityService;
import fr.utbm.da50.fastandform.core.service.VerifyService;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// role : post controller -> appeler service des entités qui va 
// récupérer le body de la requete du json en string ()

@RestController
class EditController {

    @Autowired
    private VerifyService verifservice;
    @Autowired
    private EntityService entityservice;
    @Autowired
    private WriteRepository writerep;

    @GetMapping(value = "/{entity}/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String resultctr(@PathVariable String entity,@PathVariable Integer id ) {
        return "L'entité est : " + entity + ": "+ id;
    }
    
    // exemple : http://localhost:8080/add?DatabaseName=abc&CollectionName=voiture
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody JsonNode jsonNode,@RequestParam String DatabaseName,@RequestParam String CollectionName) throws Exception {

        String data = jsonNode.toString();
        
        // transform the json request body in hashmap
        HashMap<String, Object> mappingData = JsonToHashMap(data);

        //verify the data integriy
        String res =  verifservice.verif(mappingData,CollectionName);

        //Write the data in Database
        if("ok".equals(res)){
        writerep.SaveOneDocument(DatabaseName, CollectionName, mappingData);
        }

        return res;
    }

    // exemple : http://localhost:8080/update?DatabaseName=abc&CollectionName=voiture&id=5
    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody JsonNode jsonNode,@RequestParam String DatabaseName,@RequestParam String CollectionName,@RequestParam Integer id) throws Exception{

        String data = jsonNode.toString();        
      // transform the json request body in hashmap
        HashMap<String, Object> mappingData = JsonToHashMap(data);

        //verify the data integriy
        String res =  verifservice.verif(mappingData,CollectionName);

        //Write the data in Database
        //if(res=="ok"){
        //writerep.UpdateOneDocument(DatabaseName, CollectionName, mappingData,id);
        //}

        return res;
    }

    //Convert Json to Hashmap
  public HashMap<String, Object> JsonToHashMap(String data) throws Exception {
    TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
    };
    HashMap<String, Object> mapping = new ObjectMapper().readValue(data, typeRef);
    return mapping;
  }

}