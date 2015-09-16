package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PEnum;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PSqlDate;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Order;
import org.example.domain.Order.Status;
import org.example.domain.query.QOrder;

/**
 * Association query bean for AssocOrder.
 */
@TypeQueryBean
public class QAssocOrder<R> extends TQAssocBean<Order,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public PEnum<R,Status> status;
  public PSqlDate<R> orderDate;
  public PSqlDate<R> shipDate;
  public QAssocCustomer<R> customer;
  public QAssocAddress<R> shippingAddress;
  public QAssocOrderDetail<R> details;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QOrder>... properties) {
    return fetchProperties(properties);
  }

  public QAssocOrder(String name, R root) {
    super(name, root);
  }
}
