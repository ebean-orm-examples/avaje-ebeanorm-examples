package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.OrderDetail;

/**
 * Add finder methods here.
 */
public class OrderDetailFinder extends Model.Finder<Long,OrderDetail> {

  public OrderDetailFinder() {
    super(OrderDetail.class);
  }
}
