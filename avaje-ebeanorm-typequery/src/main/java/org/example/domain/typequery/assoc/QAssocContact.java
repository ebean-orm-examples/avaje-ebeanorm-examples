package org.example.domain.typequery.assoc;

import org.avaje.ebean.typequery.property.QLong;
import org.avaje.ebean.typequery.property.QString;

public class QAssocContact<R> {

  public QLong<R> id;

  public QString<R> name;

  public QString<R> email;

  public QAssocContactNote<R> notes;

  public QAssocContact(String name, R root, String prefix) {

    String path = (prefix == null) ? name : prefix+"."+name;
    this.id = new QLong<>("id", root);
    this.name = new QString<>("name", root, path);
    this.email = new QString<>("email", root, path);
    this.notes = new QAssocContactNote<>("notes", root, path);
  }

}
