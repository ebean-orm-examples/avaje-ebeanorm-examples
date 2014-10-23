package org.example.domain;

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

  enum class Status {
    NEW
    APPROVED
    SHIPPED
    COMPLETE
  }

  public var status: Status = Status.NEW;

  public var orderDate: Date? = null;

  public var shipDate: Date? = null;

  ManyToOne NotNull
  public var customer:Customer? = null;

  ManyToOne
  public var shippingAddress: Address? = null;

  OneToMany(mappedBy="order", cascade=array(CascadeType.PERSIST))
  OrderBy("id asc")
  public var details: MutableList<OrderDetail> = ArrayList();

//  public String toString() {
//    return id + " status:" + status + " customer:" + customer;
//  }
  

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
  fun add(p:Product, orderQty: Int, unitPrice: Double) : OrderDetail {
    val d = OrderDetail();
    d.order = this
    d.set(p, orderQty, unitPrice);
    return d;
  }

  /**
   * Add an order detail
   */
  fun add(detail:OrderDetail ) {
    details.add(detail);
  }

}
