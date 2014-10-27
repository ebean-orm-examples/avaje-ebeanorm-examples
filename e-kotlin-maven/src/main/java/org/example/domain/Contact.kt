package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.avaje.ebean.Model.Finder

/**
 * Contact entity bean.
 */
Entity
Table(name = "be_contact")
public class Contact : BaseModel() {


  Column(length = 50)
  public var firstName: String? = null;

  Column(length = 50)
  public var lastName: String? = null;

  Column(length = 200)
  public var email: String? = null;

  Column(length = 20)
  public var phone: String? = null;

  ManyToOne(optional = false)
  public var customer: Customer? = null;

  /**
   * Singleton finder and alternative constructors.
   */
  class object {

    /**
     * Alternate constructor using Kotlin singleton.
     */
    fun of(first: String, last: String): Contact {
      val d = Contact();
      d.firstName = first;
      d.lastName = last;
      return d;
    }

    /**
     * Convenience Finder for 'active record' style.
     */
    public val find: Finder<String, Contact> = com.avaje.ebean.Model.Finder(javaClass<String>(), javaClass<Contact>());

  }
}
