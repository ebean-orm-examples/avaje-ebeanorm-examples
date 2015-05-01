package org.example.domain;

import com.avaje.ebean.Model
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * Product entity bean.
 */
Entity
Table(name = "o_product")
public class Product (

 Size(max = 20)
 public var sku: String? = null,

 Size(max = 100)
 public var name: String? = null

) : BaseModel() {

  /**
   * Finder convenience singleton.
   */
  companion object : Model.Find<Long, Product>() {}

}
