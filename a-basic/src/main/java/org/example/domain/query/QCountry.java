package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Country;

/**
 * Query bean for Country.
 */
@TypeQueryBean
public class QCountry extends TQRootBean<Country,QCountry> {

  private static final QCountry _alias = new QCountry(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QCountry alias() {
    return _alias;
  }

  public PString<QCountry> code;
  public PString<QCountry> name;


  /**
   * Construct with a given EbeanServer.
   */
  public QCountry(EbeanServer server) {
    super(Country.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QCountry() {
    super(Country.class);
  }

  /**
   * Construct for Alias.
   */
  private QCountry(boolean dummy) {
    super(dummy);
  }
}
