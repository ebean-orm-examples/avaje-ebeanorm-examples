package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PBoolean;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.PUtilDate;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Customer;
import org.example.domain.query.assoc.QAssocAddress;
import org.example.domain.query.assoc.QAssocContact;

@TypeQueryBean
public class QCustomer extends TQRootBean<Customer,QCustomer> {

  public PLong<QCustomer> id;
  public PLong<QCustomer> version;
  public PTimestamp<QCustomer> whenCreated;
  public PTimestamp<QCustomer> whenUpdated;
  public PBoolean<QCustomer> inactive;
  public PString<QCustomer> name;
  public PUtilDate<QCustomer> registered;
  public PString<QCustomer> comments;
  public QAssocAddress<QCustomer> billingAddress;
  public QAssocAddress<QCustomer> shippingAddress;
  public QAssocContact<QCustomer> contacts;

  /**
   * Construct using the default EbeanServer.
   */
  public QCustomer() {
    super(Customer.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public QCustomer(EbeanServer server) {
    super(Customer.class, server);
  }
}
