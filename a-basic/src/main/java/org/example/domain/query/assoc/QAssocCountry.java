package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Country;
import org.example.domain.query.QCountry;

/**
 * Association query bean for AssocCountry.
 */
@TypeQueryBean
public class QAssocCountry<R> extends TQAssocBean<Country,R> {

  public PString<R> code;
  public PString<R> name;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QCountry>... properties) {
    return fetchProperties(properties);
  }

  public QAssocCountry(String name, R root) {
    super(name, root);
  }
}
