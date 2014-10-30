package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Address entity bean.
 */
Entity
Table(name = "o_address")
public class Address : BaseModel() {

  Size(max = 100)
  public var line1: String? = null;

  Size(max = 100)
  public var line2: String? = null;

  Size(max = 100)
  public var city: String? = null;

  ManyToOne(optional=false)
  public var country: Country? = null;

  /**
   * Find helper singleton.
   */
  class object : LongIdFinder<Address>(javaClass<Address>()) {}

}


