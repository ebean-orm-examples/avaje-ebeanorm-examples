package org.example.domain;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

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
MappedSuperclass
public abstract class BaseModel : Model() {

  Id
  public var id: Long? = null;

  Version
  public var version: Long? = null;

  CreatedTimestamp
  public var whenCreated: Timestamp? = null;

  UpdatedTimestamp
  public var whenUpdated: Timestamp? = null ;

}
