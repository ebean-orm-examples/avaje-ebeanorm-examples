package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.User;

/**
 * Query bean for User.
 */
@TypeQueryBean
public class QUser extends TQRootBean<User,QUser> {

  private static final QUser _alias = new QUser(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QUser alias() {
    return _alias;
  }

  public PLong<QUser> id;
  public PString<QUser> name;


  /**
   * Construct with a given EbeanServer.
   */
  public QUser(EbeanServer server) {
    super(User.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QUser() {
    super(User.class);
  }

  /**
   * Construct for Alias.
   */
  private QUser(boolean dummy) {
    super(dummy);
  }
}
