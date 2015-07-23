package org.example.domain.query

/**
 * Created by rob on 20/07/15.
 */
public class QLongProperty<QT> (val name : String, val owner : QT) {

  fun eq(value : Long) :QT {

    return owner;
  }

  fun gt(value : Long) :QT{

    return owner;
  }

  fun gte(value : Long) {

  }

  fun lt(value : Long) {

  }

  fun lte(value : Long) {

  }

}