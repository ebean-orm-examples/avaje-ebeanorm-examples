package org.example.domain;

import com.avaje.ebean.Model
import com.avaje.ebean.annotation.CacheStrategy
import com.avaje.ebean.annotation.CacheTuning
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Country entity bean.
 * <p>
 * Uses constructor with properties having default values.
 */
@CacheStrategy(readOnly = true)
@CacheTuning(maxSize = 500)
@Entity
@Table(name = "o_country")
public class Country(

    @Id @Size(max = 2)
    public var code: String,

    @Size(max = 60)
    public var name: String

) : Model() {

  override fun toString(): String {
    return "code:$code name:$name";
  }

  /**
   * Find helper singleton.
   */
  companion object : Model.Find<String, Country>() {}

}
