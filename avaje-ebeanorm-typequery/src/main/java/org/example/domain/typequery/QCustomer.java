package org.example.domain.typequery;

import org.example.domain.Customer;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.property.QLong;
import org.avaje.ebean.typequery.property.QString;
import org.example.domain.typequery.assoc.QAssocContact;


public class QCustomer extends TQRootBean<Customer> {//},OT> {

  public QLong<QCustomer> id;

  public QString<QCustomer> name;

  public QAssocContact<QCustomer> contacts;

  public QCustomer() {
    super(Customer.class);
    this.id = new QLong<>("id", this);
    this.name = new QString<>("name", this);
    this.contacts = new QAssocContact<>("contacts", this, null);
  }

}
