package org.example.domain;

import com.avaje.ebean.Model
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.Model.Finder

/**
 * Customer entity bean.
 */
Entity
Table(name="be_customer")
public class Customer : BaseModel() {

  /**
   * Find helper singleton.
   */
  companion object: Model.Find<Long,Customer>() {}

  public var inactive:Boolean = false;
  
  Column(length=100)
  public var name: String? = null;

  public var registered: Date? = null;
  
  Column(length=1000)
  public var comments: String? = null;
  
  ManyToOne(cascade=array(CascadeType.ALL))
  public var billingAddress: Address? = null;

  ManyToOne(cascade=array(CascadeType.ALL))
  public var shippingAddress: Address? = null;

  OneToMany(mappedBy="customer", cascade=array(CascadeType.PERSIST))
  public var contacts: MutableList<Contact> = ArrayList();

  override public fun toString() : String {
    return "customer(id:$id name:$name)";
  }

  /**
   * Helper method to add a contact to the customer.
   */
  fun addContact(contact: Contact) {

    // setting the customer is automatically done when Ebean does
    // a cascade save from customer to contacts. 
    contact.customer = this;
    contacts.add(contact);
  }


  
}
