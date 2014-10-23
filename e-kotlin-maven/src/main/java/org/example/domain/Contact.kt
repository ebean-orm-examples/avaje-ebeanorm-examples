package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contact entity bean.
 */
Entity
Table(name="be_contact")
public class Contact : BaseModel() {

//  /**
//   * Convenience Finder for 'active record' style.
//   */
//  public static final Finder<Long,Contact> find = Finder<Long,Contact>(Long.class, Contact.class);
  
  Column(length=50)
  public var firstName: String? = null;
  
  Column(length=50)
  public var lastName: String? = null;
  
  Column(length=200)
  public var email: String? = null;

  Column(length=20)
  public var phone: String? = null;
  
  ManyToOne(optional=false)
  public var customer: Customer? = null;

  class object {
    fun of(first: String, last: String): Contact {
      val d = Contact();
      d.firstName = first;
      d.lastName = last;
      return d;
    }
  }
}
