package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.Address;

@TypeQueryBean
public class QAssocAddress<R> extends TQAssocBean<Address,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public PString<R> line1;
  public PString<R> line2;
  public PString<R> city;
  public QAssocCountry<R> country;

  public QAssocAddress(String name, R root) {
    super(name, root);
  }
}
