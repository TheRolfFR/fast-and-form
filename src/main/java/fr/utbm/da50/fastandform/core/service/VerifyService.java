package fr.utbm.da50.fastandform.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import fr.utbm.da50.fastandform.core.entity.EntityTemplate;
import fr.utbm.da50.fastandform.core.entity.PropertyTemplate;
import fr.utbm.da50.fastandform.core.entity.Rule;

// method verify pour les données entrantes: spring parse json body 
@Service
public class VerifyService {

  EntityService entityServ = new EntityService();
  EntityTemplate entityTemp = new EntityTemplate();

  private static final HashMap<String, String> typeInstance = initMapIns();

  private static HashMap<String, String> initMapIns() {
    HashMap<String, String> map = new HashMap<>();
    map.put("integer", "java.lang.Integer");
    map.put("float", "java.lang.Double");
    map.put("boolean", "java.lang.Boolean");
    map.put("string", "java.lang.String");
    map.put("array", "java.util.ArrayList");
    map.put("date", "java.util.Date");
    return map;
  }

  public VerifyService() {
    // TODO document why this constructor is empty
  }

  public String verif(HashMap<String, Object> mappingData, String entity) throws Exception {


    this.entityTemp = entityServ.getEntityByName(entity);
    // Get the properties of the template
    HashMap<String, PropertyTemplate> mappingTemplate = entityTemp.getProperties();

    // verify th size
    if (mappingData.size() != mappingTemplate.size()) {
       return "Le Json envoyé ne correpond pas à l'entité : " + entity;
    }
    // verify if the body contains every keys
    for (String key : mappingTemplate.keySet()) {
      if (!mappingData.containsKey(key)) {
        return "La propriété est absente : " + key;
      }

      // test if the data type is corresponding to the template, if the value is not null
      if (!(mappingData.get(key) == null)) {
        if (Boolean.FALSE.equals((verifyType(mappingData.get(key), mappingTemplate.get(key), typeInstance)))) {
          return "Mauvais type : " + key;
        }
      } 

      // Test the rules
      List<Rule> rules = mappingTemplate.get(key).getRules();
      String d = mappingTemplate.get(key).getType();
      boolean a;
      a = Dispatcher(d, rules, mappingData.get(key),typeInstance);
      if(!a){return "Non respect des règle";}

    }

    return "ok";
  }

  public Boolean Dispatcher (String d, List<Rule> rules, Object key, HashMap<String, String> typeInstance) throws ClassNotFoundException{
    Boolean a = true; 

    switch (d) {
      case "integer":
        VerifyServiceInteger verifint = new VerifyServiceInteger();
        a =  verifint.controlInteger(rules, (Integer) key);
        break;
        
      case "float":
        VerifyServiceFloat veriffloat = new VerifyServiceFloat();
        a = veriffloat.controlFloat(rules, (Double) key);
        break;

      case "string":
        VerifyServiceString verifstr = new VerifyServiceString();
        a= verifstr.controlString(rules, (String) key);
        break;

      case "boolean":
        break;

      case "date":
        break;

      case "array":
        VerifyServiceArray verifarr = new VerifyServiceArray();
        a = verifarr.controlArray(rules,key,typeInstance);
        break;

      default:
        break;
    }
    return a;
  }

  public Boolean verifyType(Object objData, PropertyTemplate objTemp, HashMap<String, String> typeInstance)
      throws ClassNotFoundException {
    String d;
    d = objTemp.getType();

    if (typeInstance.containsKey(d)) {
      return (verifyInstanceOf(objData, typeInstance.get(d)));
    }
    return true;
  }

  public Boolean verifyInstanceOf(Object obj, String type) throws ClassNotFoundException {
    return (obj.getClass() == Class.forName(type));
  }

}