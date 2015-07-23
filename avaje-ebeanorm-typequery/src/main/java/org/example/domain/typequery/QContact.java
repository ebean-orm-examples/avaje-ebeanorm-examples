package org.example.domain.typequery;

import org.example.domain.Contact;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.property.QLong;
import org.avaje.ebean.typequery.property.QString;
import org.example.domain.typequery.assoc.QAssocContactNote;

public class QContact extends TQRootBean<Contact> {//},OT> {

  public QLong<QContact> id = new QLong<QContact>("id", this);

  public QString<QContact> name = new QString<QContact>("name", this);

  public QString<QContact> email = new QString<QContact>("email", this);

  public QAssocContactNote<QContact> notes;

  public QContact() {
    super(Contact.class);
    this.notes = new QAssocContactNote<>("notes", this, null);
  }

}
