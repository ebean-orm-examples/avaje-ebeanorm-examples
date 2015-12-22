package org.example.domain;

import com.avaje.ebean.Model
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

/**
 * Customer entity bean.
 */
@Entity
@Table(name = "be_customer")
public class Customer() : BaseModel() {

  companion object : Model.Find<Long, Customer>() {}

  public var inactive: Boolean = false;

  @Size(max = 100)
  public var name: String? = null;

  public var registered: Date? = null;

  @Size(max = 1000)
  public var comments: String? = null;

  @ManyToOne(cascade = arrayOf(CascadeType.ALL))
  public var billingAddress: Address? = null;

  @ManyToOne(cascade = arrayOf(CascadeType.ALL))
  public var shippingAddress: Address? = null;

  @OneToMany(mappedBy = "customer", cascade = arrayOf(CascadeType.PERSIST))
  public var contacts: MutableList<Contact> = ArrayList();

  constructor (name: String) : this() {
    this.name = name;
  }

  override public fun toString(): String {
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
