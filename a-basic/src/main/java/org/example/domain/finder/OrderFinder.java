package org.example.domain.finder;

import com.avaje.ebean.Finder;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.Query;
import org.example.domain.Order;
import org.example.domain.query.QOrder;

import java.sql.Date;
import java.util.List;

public class OrderFinder extends Finder<Long,Order> {

  /**
   * Construct using the default EbeanServer.
   */
  public OrderFinder() {
    super(Order.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public OrderFinder(String serverName) {
    super(Order.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  protected QOrder where() {
     return new QOrder(db());
  }

  /**
   * Find orders with the given status.
   */
  public List<Order> byStatus(Order.Status... values) {

    return where()
        .status.in(values)
        .orderBy()
          .orderDate.desc()
          .id.desc()
        .findList();
  }

  /**
   * Find new orders with an orderDate after the given since value.
   */
  public PagedList<Order> newOrdersSince(Date since, int pageIndex) {

    return where()
        .status.in()
        .status.eq(Order.Status.NEW)
        .or()
          .orderDate.after(since)
          .orderDate.isNull()
        .endOr()
        .findPagedList(pageIndex, 100);
  }
}
