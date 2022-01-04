package fr.utbm.da50.fastandform.core.entity;

import java.util.HashMap;

public class EntityTemplate {

  private String name;

  private HashMap<String, PropertyTemplate> properties;

  public EntityTemplate() {
  }

  public PropertyTemplate addProperty(String name, PropertyTemplate property) {
    properties.put(name, property);
    return property;
  }

  public PropertyTemplate getProperty(String name) throws Exception {
    PropertyTemplate result = properties.get(name);

    if(result == null) throw new Exception("Could not find property " + name);

    return result;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HashMap<String,PropertyTemplate> getProperties() {
    return this.properties;
  }

  public void setProperties(HashMap<String,PropertyTemplate> properties) {
    this.properties = properties;
  }


  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", properties='" + getProperties() + "'" +
      "}";
  }
}