package org.example.domain;

import com.avaje.ebean.Model
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull

/**
 * Order Detail entity bean.
 */
@Entity
@Table(name = "o_order_detail")
public class OrderDetail() : BaseModel() {

  companion object : Model.Find<Long, Order>() {}

  /**
   * Construct with product, order quantity and unit price.
   */
  constructor(product: Product, orderQty: Int, unitPrice: Double) : this() {
    this.product = product
    this.orderQty = orderQty
    this.unitPrice = unitPrice
  }

  /**
   * The owning order - should be not null really.
   */
  @NotNull
  @ManyToOne
  public var order: Order? = null;

  public var orderQty: Int? = null;

  public var shipQty: Int? = null;

  public var unitPrice: Double? = null;

  @NotNull
  @ManyToOne
  public var product: Product? = null;

  /**
   * Helper method to set some properties.
   */
  fun set(product: Product, orderQty: Int, unitPrice: Double) {
    this.product = product;
    this.unitPrice = unitPrice
    this.orderQty = orderQty
  }


}
