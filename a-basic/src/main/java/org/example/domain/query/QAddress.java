package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Address;
import org.example.domain.query.assoc.QAssocCountry;

/**
 * Query bean for Address.
 */
@TypeQueryBean
public class QAddress extends TQRootBean<Address,QAddress> {

  private static final QAddress _alias = new QAddress(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QAddress alias() {
    return _alias;
  }

  public PLong<QAddress> id;
  public PLong<QAddress> version;
  public PTimestamp<QAddress> whenCreated;
  public PTimestamp<QAddress> whenUpdated;
  public PString<QAddress> line1;
  public PString<QAddress> line2;
  public PString<QAddress> city;
  public QAssocCountry<QAddress> country;


  /**
   * Construct with a given EbeanServer.
   */
  public QAddress(EbeanServer server) {
    super(Address.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QAddress() {
    super(Address.class);
  }

  /**
   * Construct for Alias.
   */
  private QAddress(boolean dummy) {
    super(dummy);
  }
}
