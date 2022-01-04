package fr.utbm.da50.fastandform.core.entity;

import java.util.List;
import java.util.Objects;

public class PropertyTemplate {
  private String type;

  private List<Rule> rules;

  public List<Rule> getRules() {
    return this.rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  public PropertyTemplate() {
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PropertyTemplate)) {
            return false;
        }
        PropertyTemplate propertyTemplate = (PropertyTemplate) o;
        return Objects.equals(type, propertyTemplate.type) && Objects.equals(rules, propertyTemplate.rules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, rules);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      ", rules='" + getRules() + "'" +
      "}";
  }

}