package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import com.google.gson.Gson;

public class Field implements Verifiable, Serializable, Comparable<Field> {
    private Integer id;

    private String name;

    private String description;

    private List<Rule> rules;


  public Field() {
  }

  public Field(Integer id, String name, String description, List<Rule> rules) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.rules = rules;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Rule> getRules() {
    return this.rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  public Field id(Integer id) {
    setId(id);
    return this;
  }

  public Field name(String name) {
    setName(name);
    return this;
  }

  public Field description(String description) {
    setDescription(description);
    return this;
  }

  public Field rules(List<Rule> rules) {
    setRules(rules);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Field)) {
            return false;
        }
        Field field = (Field) o;
        return Objects.equals(id, field.id) && Objects.equals(name, field.name) && Objects.equals(description, field.description) && Objects.equals(rules, field.rules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, rules);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", description='" + getDescription() + "'" +
      ", rules='" + getRules() + "'" +
      "}";
  }

  @Override
  public int compareTo(Field o) {
    int result;

    result = getId().compareTo(o.getId());
    if(result != 0) return result;

    result = getName().compareTo(o.getName());
    if(result != 0) return result;

    result = getDescription().compareTo(o.getDescription());
    if(result != 0) return result;

    return 0;
  }

  @Override
  public void verify() throws Exception {
    for (Rule rule : rules) {
      rule.verify();
    }
  }

  public String toJSON() {
    return new Gson().toJson(this);
  }

  public static Field fromJSON(String rawJSON) {
    return new Gson().fromJson(rawJSON, Field.class);
  }
}
