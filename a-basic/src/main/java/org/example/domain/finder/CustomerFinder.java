package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Customer;

/**
 * Add finder methods here.
 */
public class CustomerFinder extends Model.Finder<Long,Customer> {

  public CustomerFinder() {
    super(Customer.class);
  }

  /**
   * Find by name equal (case insensitive).
   */
  public Customer byName(String name) {
    return where().ieq("name", name).findUnique();
  }
}
