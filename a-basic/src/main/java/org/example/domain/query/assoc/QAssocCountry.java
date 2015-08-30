package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Country;

@TypeQueryBean
public class QAssocCountry<R> extends TQAssocBean<Country,R> {

  public PString<R> code;
  public PString<R> name;

  public QAssocCountry(String name, R root) {
    super(name, root);
  }
}
