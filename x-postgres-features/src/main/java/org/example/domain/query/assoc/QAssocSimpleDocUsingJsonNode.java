package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PJson;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.SimpleDocUsingJsonNode;
import org.example.domain.query.QSimpleDocUsingJsonNode;

/**
 * Association query bean for AssocSimpleDocUsingJsonNode.
 */
@TypeQueryBean
public class QAssocSimpleDocUsingJsonNode<R> extends TQAssocBean<SimpleDocUsingJsonNode,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PString<R> name;
  public PJson<R> content;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QSimpleDocUsingJsonNode>... properties) {
    return fetchProperties(properties);
  }

  public QAssocSimpleDocUsingJsonNode(String name, R root) {
    super(name, root);
  }
}
