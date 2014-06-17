package org.example.domain

import com.avaje.ebean.Model
import com.avaje.ebean.annotation.CreatedTimestamp
import com.avaje.ebean.annotation.UpdatedTimestamp
import groovy.transform.CompileStatic

import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import java.sql.Timestamp

/**
 * Base domain object with Id, version, whenCreated and whenUpdated.
 *
 * <p>
 * Extending Model to enable the 'active record' style.
 *
 * <p>
 * whenCreated and whenUpdated are generally useful for maintaining external search services (like
 * elasticsearch) and audit.
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 */
@MappedSuperclass
@CompileStatic
class BaseModel extends Model {
	@Id
	Long id

	@Version
	Long version

	@CreatedTimestamp
	Timestamp whenCreated

	@UpdatedTimestamp
	Timestamp whenUpdated
}
