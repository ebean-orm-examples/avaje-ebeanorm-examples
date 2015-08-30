package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Contact;
import org.example.domain.query.assoc.QAssocContactNote;
import org.example.domain.query.assoc.QAssocCustomer;

@TypeQueryBean
public class QContact extends TQRootBean<Contact,QContact> {

  public PLong<QContact> id;
  public PLong<QContact> version;
  public PTimestamp<QContact> whenCreated;
  public PTimestamp<QContact> whenUpdated;
  public PString<QContact> firstName;
  public PString<QContact> lastName;
  public PString<QContact> email;
  public PString<QContact> phone;
  public QAssocCustomer<QContact> customer;
  public QAssocContactNote<QContact> notes;

  /**
   * Construct using the default EbeanServer.
   */
  public QContact() {
    super(Contact.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public QContact(EbeanServer server) {
    super(Contact.class, server);
  }
}
