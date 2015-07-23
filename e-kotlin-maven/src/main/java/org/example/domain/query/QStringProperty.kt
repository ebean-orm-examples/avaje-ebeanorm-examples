package org.example.domain.query

/**
 * Created by rob on 20/07/15.
 */
public class QStringProperty<QT> (val name : String, val owner : QT) {

  fun eq(value : String) : QT {

    return owner
  }

  fun like(value : String) : QT {

    return owner
  }
}