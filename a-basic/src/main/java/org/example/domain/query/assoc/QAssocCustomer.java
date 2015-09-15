package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PBoolean;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.PUtilDate;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Customer;

@TypeQueryBean
public class QAssocCustomer<R> extends TQAssocBean<Customer,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public PBoolean<R> inactive;
  public PString<R> name;
  public PUtilDate<R> registered;
  public PString<R> comments;
  public QAssocAddress<R> billingAddress;
  public QAssocAddress<R> shippingAddress;
  public QAssocContact<R> contacts;
  public QAssocOrder<R> orders;

  public QAssocCustomer(String name, R root) {
    super(name, root);
  }
}
