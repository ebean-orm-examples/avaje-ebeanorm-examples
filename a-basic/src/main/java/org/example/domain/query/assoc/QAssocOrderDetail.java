package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PDouble;
import org.avaje.ebean.typequery.PInteger;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.OrderDetail;
import org.example.domain.query.QOrderDetail;

/**
 * Association query bean for AssocOrderDetail.
 */
@TypeQueryBean
public class QAssocOrderDetail<R> extends TQAssocBean<OrderDetail,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public QAssocOrder<R> order;
  public PInteger<R> orderQty;
  public PInteger<R> shipQty;
  public PDouble<R> unitPrice;
  public QAssocProduct<R> product;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QOrderDetail>... properties) {
    return fetchProperties(properties);
  }

  public QAssocOrderDetail(String name, R root) {
    super(name, root);
  }
}
