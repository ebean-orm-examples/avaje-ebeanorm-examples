package org.example.domain;

import com.avaje.ebean.Model
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Contact entity bean.
 */
@Entity
@Table(name = "be_contact")
public class Contact() : BaseModel() {


  @Size(max = 50)
  public var firstName: String? = null

  @Size(max = 50)
  public var lastName: String? = null

  @Size(max = 200)
  public var email: String? = null;

  @Size(max = 20)
  public var phone: String? = null;

  @NotNull
  @ManyToOne(optional = false)
  public var customer: Customer? = null;

  /**
   * Construct with firstName and lastName.
   */
  constructor(firstName: String, lastName: String) : this() {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  companion object : Model.Find<Long, Contact>() {}
}
