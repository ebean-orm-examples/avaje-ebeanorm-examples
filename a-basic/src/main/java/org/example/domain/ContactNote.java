package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ContactNote extends BaseModel {

  public static final Finder<Long, ContactNote> find = new Finder<>(Long.class, ContactNote.class);

  @ManyToOne(optional = false)
  Contact contact;

  String title;

  @Lob
  String note;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

}
