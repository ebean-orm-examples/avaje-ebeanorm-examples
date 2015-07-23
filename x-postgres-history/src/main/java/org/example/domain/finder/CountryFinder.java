package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Country;

import java.util.List;

/**
 * Add finder methods here.
 */
public class CountryFinder extends Model.Finder<String,Country> {

  public CountryFinder() {
    super(Country.class);
  }

  /**
   * Return countries with name like (case insensitive).
   */
  public List<Country> byNameLike(String name) {
    return where().ilike("name", name).findList();
  }
}
