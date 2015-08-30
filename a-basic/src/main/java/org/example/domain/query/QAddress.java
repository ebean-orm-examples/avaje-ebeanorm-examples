package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Address;
import org.example.domain.query.assoc.QAssocCountry;

@TypeQueryBean
public class QAddress extends TQRootBean<Address,QAddress> {

  public PLong<QAddress> id;
  public PLong<QAddress> version;
  public PTimestamp<QAddress> whenCreated;
  public PTimestamp<QAddress> whenUpdated;
  public PString<QAddress> line1;
  public PString<QAddress> line2;
  public PString<QAddress> city;
  public QAssocCountry<QAddress> country;

  /**
   * Construct using the default EbeanServer.
   */
  public QAddress() {
    super(Address.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public QAddress(EbeanServer server) {
    super(Address.class, server);
  }
}
