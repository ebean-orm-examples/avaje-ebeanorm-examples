package org.example.domain;

import com.avaje.ebean.Model
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
   * Convenience finder singleton and alternative constructors.
   */
  companion object : Model.Find<Long,Contact>() {

    /**
     * Alternate constructor using Kotlin singleton.
     */
    fun of(first: String, last: String): Contact {
      val d = Contact();
      d.firstName = first;
      d.lastName = last;
      return d;
    }

  }
}
