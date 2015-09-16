package org.example.domain.finder;

import com.avaje.ebean.Finder;
import org.example.domain.Address;
import org.example.domain.query.QAddress;

import java.util.List;

public class AddressFinder extends Finder<Long,Address> {

  /**
   * Construct using the default EbeanServer.
   */
  public AddressFinder() {
    super(Address.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public AddressFinder(String serverName) {
    super(Address.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  protected QAddress where() {
     return new QAddress(db());
  }

  /**
   * Find all addresses with city like.
   */
  public List<Address> byCityLike(String city) {
    return where().city.like(city).findList();
  }
}
