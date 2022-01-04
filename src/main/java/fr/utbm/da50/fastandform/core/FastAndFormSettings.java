package fr.utbm.da50.fastandform.core;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// common wya to build complex configuration files
// https://www.youtube.com/watch?v=z8kfFbfGGME
@Configuration
@ConfigurationProperties(prefix="ff")
public class FastAndFormSettings {

  private String[] entitiesLocation;

  private static FastAndFormSettings instance;

  public String[] getEntitiesLocation() {
    return this.entitiesLocation;
  }

  public void setEntitiesLocation(String[] entitiesLocation) {
    this.entitiesLocation = entitiesLocation;
  }
  
  public static FastAndFormSettings getInstance() {
    return instance;
  }

  public static FastAndFormSettings setInstance(FastAndFormSettings settings) {
    if(instance == null) {
      instance = settings;
    }

    return instance;
  }

  public FastAndFormSettings() {
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FastAndFormSettings)) {
            return false;
        }
        FastAndFormSettings fastAndFormSettings = (FastAndFormSettings) o;
        return Objects.equals(entitiesLocation, fastAndFormSettings.entitiesLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(entitiesLocation);
  }

  @Override
  public String toString() {
    return "{" +
      " entities_location='" + getEntitiesLocation() + "'" +
      "}";
  }

}
