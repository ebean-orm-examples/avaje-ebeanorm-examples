package org.avaje.ebean.typequery.property;

public class QString<R> extends QProperty<R> {//} {

  public QString(String name, R owner) {
    super(name, owner);
  }

  public QString(String name, R owner, String prefix) {
    super(name, owner, prefix);
  }

  public R eq(String value) {
    expr().eq(name, value);
    return root;
  }

  public R like(String value) {
    expr().like(name, value);
    return root;
  }
}
