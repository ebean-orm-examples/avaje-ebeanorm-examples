package org.example.domain;

import com.avaje.ebean.annotation.History;
import com.avaje.ebean.annotation.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 */
//@History
@Entity
@Table(name = "customer")
public class Customer extends BaseModel {

  @Index()
  @Column(length = 50, nullable = false)
  String name;

  String shortDesc;

  @Column(length = 50)
  String fooButReallyLooooooooooooooooog;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getFooButReallyLooooooooooooooooog() {
    return fooButReallyLooooooooooooooooog;
  }

  public void setFooButReallyLooooooooooooooooog(String fooButReallyLooooooooooooooooog) {
    this.fooButReallyLooooooooooooooooog = fooButReallyLooooooooooooooooog;
  }
}
