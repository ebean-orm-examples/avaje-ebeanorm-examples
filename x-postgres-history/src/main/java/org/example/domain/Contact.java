package org.example.domain;

import com.avaje.ebean.annotation.History;
import org.example.domain.finder.ContactFinder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contact entity bean.
 */
@History
@Entity
@Table(name="contact")
public class Contact extends BaseModel {

  /**
   * Convenience Finder for 'active record' style.
   */
  public static final ContactFinder find = new ContactFinder();

  @ManyToOne(optional=false)
  Customer customer;

  @Column(length=50)
  String firstName;
  
  @Column(length=50)
  String lastName;
  
  @Column(length=200)
  String email;

  @Column(length=20)
  String phone;

  /**
   * Default constructor.
   */
  public Contact() {
  }
  
  /**
   * Construct with a firstName and lastName.
   */
  public Contact(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}
