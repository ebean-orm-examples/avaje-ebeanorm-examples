package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Contact;

import java.util.List;

/**
 * Add finder methods here.
 */
public class ContactFinder extends Model.Finder<Long,Contact> {

  public ContactFinder() {
    super(Contact.class);
  }

  /**
   * Find the contact by exact email address.
   */
  public Contact byEmail(String email) {
    return where().eq("email", email).findUnique();
  }

  /**
   * Find by email address ending in (case insensitive).
   */
  public List<Contact> byEmailEndsWith(String emailEndsIn) {
    return where().iendsWith("email", emailEndsIn).findList();
  }

}
