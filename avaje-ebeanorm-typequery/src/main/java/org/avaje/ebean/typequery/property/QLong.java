package org.avaje.ebean.typequery.property;

public class QLong<OT> extends QProperty<OT> {

  public QLong(String name, OT owner) {
    super(name , owner);
  }

  public OT eq(long value) {
    expr().eq(name, value);
    return root;
  }

  public OT gt(long value) {
    expr().gt(name, value);
    return root;
  }

  public OT lt(long value) {
    expr().lt(name, value);
    return root;
  }

}
