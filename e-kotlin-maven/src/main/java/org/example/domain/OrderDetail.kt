package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Order Detail entity bean.
 */
Entity
Table(name = "o_order_detail")
public class OrderDetail : BaseModel() {

  /**
   * Find helper singleton.
   */
  class object: LongIdFinder<Order>(javaClass<Order>()) {

    /**
     * A constructor method - hmmm.
     */
    fun of(p:Product, orderQty: Int, unitPrice: Double) : OrderDetail {
      val d = OrderDetail();
      d.set(p, orderQty, unitPrice);
      return d;
    }
  }

  /**
   * The owning order - should be not null really.
   */
  ManyToOne
  public var order: Order? = null;

  public var orderQty: Int? = null;

  public var shipQty: Int? = null;

  public var unitPrice: Double? = null;

  ManyToOne
  public var product: Product? = null;

  /**
   * Helper method to set some properties.
   */
  fun set(p:Product, orderQty: Int, unitPrice: Double) {
    this.product = p;
    this.unitPrice = unitPrice
    this.orderQty = orderQty
  }


}
