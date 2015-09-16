package org.example.domain.query.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQProperty;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.ContactNote;
import org.example.domain.query.QContactNote;

/**
 * Association query bean for AssocContactNote.
 */
@TypeQueryBean
public class QAssocContactNote<R> extends TQAssocBean<ContactNote,R> {

  public PLong<R> id;
  public PLong<R> version;
  public PTimestamp<R> whenCreated;
  public PTimestamp<R> whenUpdated;
  public QAssocContact<R> contact;
  public PString<R> title;
  public PString<R> note;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QContactNote>... properties) {
    return fetchProperties(properties);
  }

  public QAssocContactNote(String name, R root) {
    super(name, root);
  }
}
