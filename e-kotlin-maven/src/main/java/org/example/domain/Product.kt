package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.avaje.ebean.Model.Finder


/**
 * Product entity bean.
 */
Entity
Table(name = "o_product")
public class Product (

 Size(max = 20)
 public var sku: String? = null,

 public var name: String? = null

) : BaseModel() {

  /**
   * Finder convenience singleton.
   */
  class object : LongIdFinder<Product>(javaClass<Product>()) {}

}
