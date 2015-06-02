package org.example.domain;

import com.avaje.ebean.Model
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Order entity bean.
 */
Entity
Table(name = "o_order")
public class Order : BaseModel() {

  companion object: Model.Find<Long, Order>(){}

  enum class Status {
    NEW,
    APPROVED,
    SHIPPED,
    COMPLETE
  }

  public var status: Status = Status.NEW;

  public var orderDate: Date? = null;

  public var shipDate: Date? = null;

  ManyToOne NotNull
  public var customer:Customer? = null;

  ManyToOne
  public var shippingAddress: Address? = null;

  OneToMany(mappedBy = "order", cascade = arrayOf(CascadeType.PERSIST))
  OrderBy("id asc")
  public var details: MutableList<OrderDetail> = ArrayList();
  

  /**
   * Set the customer with their current shipping address.
   */
  fun setCustomerWithShipping(customer:Customer) {
    this.customer = customer;
    this.shippingAddress = customer.shippingAddress;
  }

  /**
   * Add an order detail.
   */
  fun add(product:Product, orderQty: Int, unitPrice: Double) : OrderDetail {
    val detail = OrderDetail();
    detail.order = this
    detail.set(product, orderQty, unitPrice);
    return detail;
  }

  /**
   * Add an order detail
   */
  fun add(detail:OrderDetail ) {
    details.add(detail);
  }

}
