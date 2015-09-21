package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PJson;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.SimpleDoc;

/**
 * Query bean for SimpleDoc.
 */
@TypeQueryBean
public class QSimpleDoc extends TQRootBean<SimpleDoc,QSimpleDoc> {

  private static final QSimpleDoc _alias = new QSimpleDoc(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QSimpleDoc alias() {
    return _alias;
  }

  public PLong<QSimpleDoc> id;
  public PLong<QSimpleDoc> version;
  public PString<QSimpleDoc> name;
  public PJson<QSimpleDoc> content;


  /**
   * Construct with a given EbeanServer.
   */
  public QSimpleDoc(EbeanServer server) {
    super(SimpleDoc.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QSimpleDoc() {
    super(SimpleDoc.class);
  }

  /**
   * Construct for Alias.
   */
  private QSimpleDoc(boolean dummy) {
    super(dummy);
  }
}
