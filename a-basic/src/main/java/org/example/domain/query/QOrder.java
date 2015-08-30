package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PEnum;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PSqlDate;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Order;
import org.example.domain.Order.Status;
import org.example.domain.query.assoc.QAssocAddress;
import org.example.domain.query.assoc.QAssocCustomer;
import org.example.domain.query.assoc.QAssocOrderDetail;

@TypeQueryBean
public class QOrder extends TQRootBean<Order,QOrder> {

  public PLong<QOrder> id;
  public PLong<QOrder> version;
  public PTimestamp<QOrder> whenCreated;
  public PTimestamp<QOrder> whenUpdated;
  public PEnum<QOrder,Status> status;
  public PSqlDate<QOrder> orderDate;
  public PSqlDate<QOrder> shipDate;
  public QAssocCustomer<QOrder> customer;
  public QAssocAddress<QOrder> shippingAddress;
  public QAssocOrderDetail<QOrder> details;

  /**
   * Construct using the default EbeanServer.
   */
  public QOrder() {
    super(Order.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public QOrder(EbeanServer server) {
    super(Order.class, server);
  }
}
