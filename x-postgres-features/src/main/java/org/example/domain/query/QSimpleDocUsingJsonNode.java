package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PJson;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.SimpleDocUsingJsonNode;

/**
 * Query bean for SimpleDocUsingJsonNode.
 */
@TypeQueryBean
public class QSimpleDocUsingJsonNode extends TQRootBean<SimpleDocUsingJsonNode,QSimpleDocUsingJsonNode> {

  private static final QSimpleDocUsingJsonNode _alias = new QSimpleDocUsingJsonNode(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QSimpleDocUsingJsonNode alias() {
    return _alias;
  }

  public PLong<QSimpleDocUsingJsonNode> id;
  public PLong<QSimpleDocUsingJsonNode> version;
  public PString<QSimpleDocUsingJsonNode> name;
  public PJson<QSimpleDocUsingJsonNode> content;


  /**
   * Construct with a given EbeanServer.
   */
  public QSimpleDocUsingJsonNode(EbeanServer server) {
    super(SimpleDocUsingJsonNode.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QSimpleDocUsingJsonNode() {
    super(SimpleDocUsingJsonNode.class);
  }

  /**
   * Construct for Alias.
   */
  private QSimpleDocUsingJsonNode(boolean dummy) {
    super(dummy);
  }
}
