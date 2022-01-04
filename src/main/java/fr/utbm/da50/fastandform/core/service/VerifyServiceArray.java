package fr.utbm.da50.fastandform.core.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.internal.function.text.Length;

import fr.utbm.da50.fastandform.core.entity.Rule;

public class VerifyServiceArray {

    public VerifyServiceArray() {
    }

    public Boolean controlArray(List<Rule> rules, Object data, HashMap<String,String> mapInstance) throws ClassNotFoundException{
        Integer i = 1;
        Boolean b = false;
        String id;
        String type;
        Map<String, Object> options;

        //verify data is not null
        if(data==null){
            for (Rule x : rules) {
                id = x.getId();
                if("required".equals(id)){
                    return false;
                }
            }
            return true;
        }

        //convert object to ArrayList 
        List<String> listdata= new ArrayList<>();
        listdata = convertObjectToString(data);
        Integer size = listdata.size();

        for (Rule x : rules) {
            id = x.getId();
            type = x.getType();
            options = x.getOptions();

            if("length".equals(id)){
                b = length(options,size);
                if(Boolean.FALSE.equals(b)){return false ;}
            }
            if("correct_types".equals(id)){
                b = correctValue(options,listdata,mapInstance);
                if(Boolean.FALSE.equals(b)){return false;}
            }
         
        }
        return true;
    }

    private Boolean correctValue(Map<String, Object> x, List<String> listdata, HashMap<String, String> mapInstance) throws ClassNotFoundException {
        List<String> listval = new ArrayList<>();
        listval = convertObjectToString(x.get("values"));

        for(String s : listdata){
             if(!listval.contains(s)){return false;}
        }
        return true ;
    }

    public Boolean length(Map<String, Object> x, Integer data ){
        Double var;
        Integer min, max, value;
        boolean b = true, c = true;

        if (x.containsKey("value")) {
            var = (Double) x.get("value");
            value = var.intValue();
            return (data == value);
        }
        if (x.containsKey("min")) {
            var = (Double) x.get("min");
            min = var.intValue();
            b = (data >= min);
        }
        if (x.containsKey("max")) {
            var = (Double) x.get("max");
            max = var.intValue();
            c = (data <= max);
        }

        return (b && c);

    }

    public List<String> convertObjectToString (Object data){

        List<String> liststr = new ArrayList<>();
        String str = data.toString();
        str = str.replace("[", "");
        str = str.replace("]", "");
        String[] items = str.split(",");
        liststr = Arrays.asList(items);
        return liststr ;
    }

    public Boolean verifyType(Object objData, String d, HashMap<String, String> typeInstance)
    throws ClassNotFoundException {
        if (typeInstance.containsKey(d)) {
        return (objData.getClass() == Class.forName(d));
        }
        return true;
        }
}

