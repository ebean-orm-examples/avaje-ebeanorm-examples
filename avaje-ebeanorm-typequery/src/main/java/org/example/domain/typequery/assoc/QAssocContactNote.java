package org.example.domain.typequery.assoc;

import org.avaje.ebean.typequery.property.QLong;
import org.avaje.ebean.typequery.property.QString;

public class QAssocContactNote<R> {//extends QAssocOne<Contact,OWNER>  {

  public QLong<R> id;

  public QString<R> title;

  public QString<R> note;

  public QAssocContactNote(String name, R root, String prefix) {

    String path = (prefix == null) ? name : prefix+"."+name;
    this.id = new QLong<>("id", root);
    this.title = new QString<>("title", root, path);
    this.note = new QString<>("note", root, path);
  }

}
