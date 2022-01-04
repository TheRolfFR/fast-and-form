package fr.utbm.da50.fastandform.core.service;

import java.util.List;
import java.util.Map;

import fr.utbm.da50.fastandform.core.entity.Rule;

public class VerifyServiceInteger {

    public VerifyServiceInteger() {
        // TODO document why this constructor is empty
    }

    public Boolean controlInteger(List<Rule> rules, Integer data) {
        int i = 1;
        Boolean b = false, a=true;
        String id,type;
        Map<String, Object> options;

        if(data==null){
            for (Rule x : rules) {
                id = x.getId();
                if("required".equals(id)){
                    return false;
                }
            }
            return true;
        }

        for (Rule x : rules) {
          id = x.getId();
          type = x.getType();
          options = x.getOptions();

          if("length".equals(id)){
                b = length(options, data);
                if(Boolean.FALSE.equals(b)){return false;}
          }
          if("intervalle".equals(id)){
              b = intervalle(options, data);
              if(Boolean.FALSE.equals(b)){return false;}
          }
        }

        return a;
    }

    public Boolean intervalle(Map<String, Object> x, Integer data) {
        Double var;
        Integer min, max;
        boolean b = true, c = true;

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

    public Boolean length(Map<String, Object> x, Integer data) {
        Double var;
        Integer min, max, value;
        boolean b = true, c = true;

        if (x.containsKey("value")) {
            var = (Double) x.get("value");
            value = var.intValue();
            return (String.valueOf(data).length() == value);
        }
        if (x.containsKey("min")) {
            var = (Double) x.get("min");
            min = var.intValue();
            b = (String.valueOf(data).length() >= min);
        }
        if (x.containsKey("max")) {
            var = (Double) x.get("max");
            max = var.intValue();
            c = (String.valueOf(data).length() <= max);
        }

        return (b && c);
    }

}
