package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.User;
import org.example.domain.query.QUser;

/**
 * Association query bean for AssocUser.
 */
@TypeQueryBean
public class QAssocUser<R> extends TQAssocBean<User,R> {

  public PLong<R> id;
  public PString<R> name;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QUser>... properties) {
    return fetchProperties(properties);
  }

  public QAssocUser(String name, R root) {
    super(name, root);
  }
}
