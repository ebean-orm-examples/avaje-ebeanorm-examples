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

/**
 * Query bean for Customer.
 */
@TypeQueryBean
public class QCustomer extends TQRootBean<Customer,QCustomer> {

  private static final QCustomer _alias = new QCustomer(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QCustomer alias() {
    return _alias;
  }

  public PLong<QCustomer> id;
  public PLong<QCustomer> version;
  public PTimestamp<QCustomer> whenCreated;
  public PTimestamp<QCustomer> whenUpdated;
  public PBoolean<QCustomer> inactive;
  public PString<QCustomer> name;
  public PUtilDate<QCustomer> registered;
  public PString<QCustomer> comments;


  /**
   * Construct with a given EbeanServer.
   */
  public QCustomer(EbeanServer server) {
    super(Customer.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QCustomer() {
    super(Customer.class);
  }

  /**
   * Construct for Alias.
   */
  private QCustomer(boolean dummy) {
    super(dummy);
  }
}
