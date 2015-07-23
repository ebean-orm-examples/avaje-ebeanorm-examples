package org.example.domain;

import com.avaje.ebean.annotation.History;
import org.example.domain.finder.CustomerFinder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Customer entity bean.
 */
@History
@Entity
@Table(name="customer")
public class Customer extends BaseModel {

  /**
   * Convenience Finder for 'active record' style.
   */
  public static final CustomerFinder find = new CustomerFinder();
  
//  Boolean inactive;
  
  @Size(max = 100)
  String name;

  LocalDate registered;

  @Size(max = 1000)
  String comments;

  @ManyToOne(cascade=CascadeType.ALL)
  Address billingAddress;

  @ManyToOne(cascade=CascadeType.ALL)
  Address shippingAddress;

  @OneToMany(mappedBy="customer")
  List<Contact> contacts;


  public String toString() {
    return "id"+id+" name:"+name+" comments:"+comments;
  }

//  public Boolean isInactive() {
//    return inactive;
//  }
//
//  public void setInactive(boolean inactive) {
//    this.inactive = inactive;
//  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDate registered) {
    this.registered = registered;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
}
