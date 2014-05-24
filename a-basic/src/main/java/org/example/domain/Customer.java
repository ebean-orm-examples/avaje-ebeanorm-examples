package org.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="be_customer")
public class Customer extends BaseModel {

  /**
   * Finder uses the 'default' EbeanServer.
   */
  public static Finder<Long,Customer> find = new Finder<Long,Customer>(Long.class, Customer.class);
  
  String name;

  Date registered;
  
  String comments;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getRegistered() {
    return registered;
  }

  public void setRegistered(Date registered) {
    this.registered = registered;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
  
  
}
