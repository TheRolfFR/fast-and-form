package fr.utbm.da50.fastandform.core.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fr.utbm.da50.fastandform.core.entity.Rule;

public class VerifyServiceString {

    public VerifyServiceString() {
        // TODO document why this constructor is empty
    }

    public Boolean controlString(List<Rule> rules, String data) {
        int i = 1;
        Boolean b = false, a = true;
        String id, type;
        Map<String, Object> options;

        if (data == null) {
            for (Rule x : rules) {
                id = x.getId();
                if ("required".equals(id)) {
                    return false;
                }
            }
            return true;
        }

        for (Rule x : rules) {
            id = x.getId();
            type = x.getType();
            options = x.getOptions();

            if ("length".equals(id)) {
                b = length(options, data);
                if (Boolean.FALSE.equals(b)) {
                    return false;
                }
            }
            if ("regex".equals(id)) {
                b = regex(options, data);
                if (Boolean.FALSE.equals(b)) {
                    return false;
                }
            }
        }

        return a;
    }

    public Boolean length(Map<String, Object> x, String data) {
        Double var;
        Integer min, max, value;
        boolean b = true, c = true;

        if (x.containsKey("value")) {
            var = (Double) x.get("value");
            value = var.intValue();
            return (data.length() == value);
        }
        if (x.containsKey("min")) {
            var = (Double) x.get("min");
            min = var.intValue();
            b = (data.length() >= min);
        }
        if (x.containsKey("max")) {
            var = (Double) x.get("max");
            max = var.intValue();
            c = (data.length() <= max);
        }

        return (b && c);
    }

    public Boolean regex(Map<String, Object> x, String data) {
        Boolean b = true;
        if (x.containsKey("value")) {
            String regex = (String) x.get("value");
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(data).matches()) {
                b = false;
            }

        }
        return b;
    }

}
