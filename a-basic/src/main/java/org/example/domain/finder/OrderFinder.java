package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Order;

/**
 * Add finder methods here.
 */
public class OrderFinder extends Model.Finder<Long,Order> {

  public OrderFinder() {
    super(Order.class);
  }
}
