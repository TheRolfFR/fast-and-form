package fr.utbm.da50.fastandform.core.service;

import org.springframework.stereotype.Service;

import fr.utbm.da50.fastandform.core.entity.EntityTemplate;
@Service
public class EntityService {
    
    public static EntityTemplate getEntityByName (String entityName) throws Exception {
        return null;
    }

    public String add(String a){
        return a;
    }
    public String update(String a){
        return a;
    }
    public Boolean delete(Integer a){
        return true;
    }
}
