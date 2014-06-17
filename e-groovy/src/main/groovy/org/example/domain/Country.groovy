package org.example.domain

import com.avaje.ebean.annotation.CacheStrategy
import com.avaje.ebean.annotation.CacheTuning
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Country entity bean.
 */
@CacheStrategy(readOnly=true,warmingQuery="order by name")
@CacheTuning(maxSize=500)
@Entity
@Table(name="o_country")
@CompileStatic
class Country {
	@Id
	@Size(max=2)
	String code

	@Size(max=60)
	String name

	public String toString() {
		return code
	}
}
