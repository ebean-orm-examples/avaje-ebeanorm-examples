package org.example.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customer entity bean.
 */
@Entity
@Table(name="be_customer")
public class Customer extends BaseModel {

  /**
   * Convenience Finder for 'active record' style.
   */
  public static final Finder<Long,Customer> find = new Finder<>(Long.class, Customer.class);
  
  @Column(length=100)
  String name;

  Date registered;
  
  @Column(length=1000)
  String comments;
  
  @OneToMany(mappedBy="customer")
  List<Contact> contacts;

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

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
  
}
