package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;


public class Rule implements Verifiable, Serializable, Comparable<Rule> {
  
  private String id;

  private String type;

  private Map<String, Object> options;

  public Rule() {
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String,Object> getOptions() {
    return this.options;
  }

  public void setOptions(Map<String,Object> options) {
    this.options = options;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) o;
        return Objects.equals(id, rule.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
  

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", type='" + getType() + "'" +
      ", options='" + getOptions() + "'" +
      "}";
  }


  @Override
  public void verify() throws Exception {
    throw new Exception("Abstract class");
  }

  /**
   * Compares the two rules
   * @author TheRolf
   * ID Must be unique, because they are supposed to reusable
   */
  @Override
  public int compareTo(Rule o) {
    return this.getId().compareTo(o.getId());
  }
}