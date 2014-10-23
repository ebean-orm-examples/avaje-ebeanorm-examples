package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.avaje.ebean.annotation.CacheStrategy;
import com.avaje.ebean.annotation.CacheTuning;
import com.avaje.ebean.Model.Finder

/**
 * Country entity bean.
 */
CacheStrategy(readOnly = true, warmingQuery = "order by name")
CacheTuning(maxSize = 500)
Entity
Table(name = "o_country")
public class Country {

  class object {

    /**
     * Convenience Finder for 'active record' style.
     */
    public val find: Finder<String, Country> = Finder(javaClass<String>(), javaClass<Country>());

    /**
     * Return a reference bean
     *
     * @param id the key for the country
     */
    public fun ref(id: String): Country {
      return find.ref(id);
    }
  }

  Id Size(max = 2)
  public var code: String? = null;

  Size(max = 60)
  public var name: String? = null;

}
