package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.ContactNote;
import org.example.domain.query.assoc.QAssocContact;

@TypeQueryBean
public class QContactNote extends TQRootBean<ContactNote,QContactNote> {

  public PLong<QContactNote> id;
  public PLong<QContactNote> version;
  public PTimestamp<QContactNote> whenCreated;
  public PTimestamp<QContactNote> whenUpdated;
  public QAssocContact<QContactNote> contact;
  public PString<QContactNote> title;
  public PString<QContactNote> note;

  /**
   * Construct using the default EbeanServer.
   */
  public QContactNote() {
    super(ContactNote.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public QContactNote(EbeanServer server) {
    super(ContactNote.class, server);
  }
}
