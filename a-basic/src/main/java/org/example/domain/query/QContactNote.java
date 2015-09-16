package org.example.domain.query;

import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.PTimestamp;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.TypeQueryBean;
import org.example.domain.ContactNote;
import org.example.domain.query.assoc.QAssocContact;

/**
 * Query bean for ContactNote.
 */
@TypeQueryBean
public class QContactNote extends TQRootBean<ContactNote,QContactNote> {

  private static final QContactNote _alias = new QContactNote(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QContactNote alias() {
    return _alias;
  }

  public PLong<QContactNote> id;
  public PLong<QContactNote> version;
  public PTimestamp<QContactNote> whenCreated;
  public PTimestamp<QContactNote> whenUpdated;
  public QAssocContact<QContactNote> contact;
  public PString<QContactNote> title;
  public PString<QContactNote> note;


  /**
   * Construct with a given EbeanServer.
   */
  public QContactNote(EbeanServer server) {
    super(ContactNote.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QContactNote() {
    super(ContactNote.class);
  }

  /**
   * Construct for Alias.
   */
  private QContactNote(boolean dummy) {
    super(dummy);
  }
}
