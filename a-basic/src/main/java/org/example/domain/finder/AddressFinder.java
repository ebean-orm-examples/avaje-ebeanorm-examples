package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Address;

import java.util.List;

/**
 * Add finder methods here.
 */
public class AddressFinder extends Model.Finder<Long,Address> {

  public AddressFinder() {
    super(Address.class);
  }

  /**
   * Find all addresses with city like.
   */
  public List<Address> byCityLike(String city) {
    return where().like("city", city).findList();
  }
}
