package org.avaje.ebean.typequery.property;

import com.avaje.ebean.ExpressionList;
import org.avaje.ebean.typequery.ExpressionOwner;

public class QProperty<R> {

  protected final String localName;

  protected final String name;

  protected final R root;

  public QProperty(String name, R root) {
    this(name, root, null);
  }

  public QProperty(String name, R root, String prefix) {
    this.localName = name;
    this.root = root;
    this.name = fullName(prefix);
  }

  public ExpressionList<?> expr() {
    return ((ExpressionOwner) root).expr();
  }

  protected String fullName(String prefix) {
    if (prefix != null) {
      return prefix + "." + localName;
    } else {
      return localName;
    }
  }

  public R asc() {
    expr().order().asc(name);
    return root;
  }

  public R desc() {
    expr().order().desc(name);
    return root;
  }

}
