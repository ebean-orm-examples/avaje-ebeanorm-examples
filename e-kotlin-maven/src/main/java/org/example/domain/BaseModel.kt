package org.example.domain;

import com.avaje.ebean.Model
import com.avaje.ebean.annotation.WhenCreated
import com.avaje.ebean.annotation.WhenModified
import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base domain object with Id, version, whenCreated and whenUpdated.
 *
 * <p>
 * Extending Model to enable the 'active record' style.
 *
 * <p>
 * whenCreated and whenUpdated are generally useful for maintaining external search services (like
 * elasticsearch) and audit.
 */
@MappedSuperclass
public abstract class BaseModel : Model() {

  @Id
  public var id: Long? = null

  @Version
  public var version: Long? = null

  @WhenCreated
  public var whenCreated: Timestamp? = null

  @WhenModified
  public var whenModified: Timestamp? = null

}
