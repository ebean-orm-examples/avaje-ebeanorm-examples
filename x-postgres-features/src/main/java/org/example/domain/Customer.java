package org.example.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avaje.ebean.annotation.DbHstore;

/**
 * Customer entity bean.
 */
@Entity
@Table(name="p_customer")
public class Customer extends BaseModel {

  /**
   * Convenience Finder for 'active record' style.
   */
  public static final Finder<Long,Customer> find = new Finder<>(Customer.class);
  
  boolean inactive;
  
  @Column(length=100)
  String name;

  Date registered;
  
  @Column(length=1000)
  String comments;

  @DbHstore
  Map<String, String> tags;

  public String toString() {
    return "id:"+id+" name:"+name+" tags:"+tags;
  }
  
  public boolean isInactive() {
    return inactive;
  }

  public void setInactive(boolean inactive) {
    this.inactive = inactive;
  }
  
  public Map<String, String> getTags() {
    return tags;
  }

  public void setTags(Map<String, String> tags) {
    this.tags = tags;
  }

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
