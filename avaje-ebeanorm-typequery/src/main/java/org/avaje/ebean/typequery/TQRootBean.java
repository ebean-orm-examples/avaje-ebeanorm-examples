package org.avaje.ebean.typequery;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;

import java.util.List;

public class TQRootBean<T> implements ExpressionOwner {//}, OT> {

  //protected O root;

  final EbeanServer server;

  final Class<T> beanType;

  private final Query<T> query;

  public TQRootBean(Class<T> beanType) {
    this(beanType, Ebean.getDefaultServer());
  }

  public TQRootBean(Class<T> beanType, EbeanServer server) {
    this.beanType  = beanType;
    this.server = server;
    this.query = server.find(beanType);
    ExpressionList<T> where = query.where();
  }

//  void setOwner(O root) {
//    this.root = root;
//  }

  public Query<T> getQuery() {
    return query;
  }

  public EbeanServer getServer() {
    return server;
  }

  public Class<T> getBeanType() {
    return beanType;
  }

//  public O or() {
//    query.w
//  }

//  public T orderBy() {
//    return this;
//  }

  public T findUnique() {
    return query.findUnique();
  }

  public List<T> findList() {
    return query.findList();
  }

  @Override
  public ExpressionList<T> expr() {
    return query.where();
  }
}
