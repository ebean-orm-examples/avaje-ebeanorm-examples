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

/**
 * Query bean for Order.
 */
@TypeQueryBean
public class QOrder extends TQRootBean<Order,QOrder> {

  private static final QOrder _alias = new QOrder(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QOrder alias() {
    return _alias;
  }

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
   * Construct with a given EbeanServer.
   */
  public QOrder(EbeanServer server) {
    super(Order.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QOrder() {
    super(Order.class);
  }

  /**
   * Construct for Alias.
   */
  private QOrder(boolean dummy) {
    super(dummy);
  }
}
