package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.ContactNote;

@TypeQueryBean
public class QAssocContactNote<R> extends TQAssocBean<ContactNote,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public QAssocContact<R> contact;
  public PString<R> title;
  public PString<R> note;

  public QAssocContactNote(String name, R root) {
    super(name, root);
  }
}
