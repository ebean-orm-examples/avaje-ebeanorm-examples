package org.example.domain;

import com.avaje.ebean.Model
import java.sql.Date
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Order entity bean.
 */
@Entity
@Table(name = "o_order")
public class Order : BaseModel() {

  companion object : Model.Find<Long,Order>() {}

  enum class Status {
    NEW,
    APPROVED,
    SHIPPED,
    COMPLETE
  }

  public var status: Status = Status.NEW;

  public var orderDate: Date? = null;

  public var shipDate: Date? = null;

  @ManyToOne @NotNull
  public var customer: Customer? = null;

  @ManyToOne
  public var shippingAddress: Address? = null;

  @OneToMany(mappedBy = "order", cascade = arrayOf(CascadeType.PERSIST))
  @OrderBy("id asc")
  public var details: MutableList<OrderDetail> = ArrayList();


}
