package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PDouble;
import org.avaje.ebean.typequery.PInteger;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.OrderDetail;
import org.example.domain.query.assoc.QAssocOrder;
import org.example.domain.query.assoc.QAssocProduct;

/**
 * Query bean for OrderDetail.
 */
@TypeQueryBean
public class QOrderDetail extends TQRootBean<OrderDetail,QOrderDetail> {

  private static final QOrderDetail _alias = new QOrderDetail(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QOrderDetail alias() {
    return _alias;
  }

  public PLong<QOrderDetail> id;
  public PLong<QOrderDetail> version;
  public PTimestamp<QOrderDetail> whenCreated;
  public PTimestamp<QOrderDetail> whenUpdated;
  public QAssocOrder<QOrderDetail> order;
  public PInteger<QOrderDetail> orderQty;
  public PInteger<QOrderDetail> shipQty;
  public PDouble<QOrderDetail> unitPrice;
  public QAssocProduct<QOrderDetail> product;


  /**
   * Construct with a given EbeanServer.
   */
  public QOrderDetail(EbeanServer server) {
    super(OrderDetail.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QOrderDetail() {
    super(OrderDetail.class);
  }

  /**
   * Construct for Alias.
   */
  private QOrderDetail(boolean dummy) {
    super(dummy);
  }
}
