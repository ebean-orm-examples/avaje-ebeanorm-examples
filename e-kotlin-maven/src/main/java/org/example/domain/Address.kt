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

}
//  /**
//   * Create a copy of the address. Used to provide a 'snapshot' of
//   * the shippingAddress for a give order.
//   */
//  public Address createCopy() {
//    Address copy = new Address();
//    copy.setLine1(line1);
//    copy.setLine2(line2);
//    copy.setCity(city);
//    copy.setCountry(country);
//    return copy;
//  }

//  public String toString() {
//    return id + " " + line1 + " " + line2 + " " + city + " " + country;
//  }



