package org.avaje.ebean.typequery._ajunk;


import org.avaje.ebean.typequery.property.QPropertyChain;

public class QAssocOne<TARGET,OT> {//implements QPropertyChain {

  protected final String _name;

  protected final OT owner;

  protected final Class<TARGET> targetClass;

  protected final QPropertyChain parentChain;

  public QAssocOne(String name, Class<TARGET> targetClass, OT owner) {
    this._name = name;
    this.targetClass = targetClass;
    this.owner = owner;
    if (owner instanceof QPropertyChain) {
      parentChain = (QPropertyChain)owner;
    } else {
      parentChain = null;
    }
  }

//  @Override
//  public String getPrefix() {
//    if (parentChain != null) {
//      return parentChain.getPrefix()+"."+name;
//    }
//    return name;
//  }
}
