package org.example.domain

import com.avaje.ebean.Model
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Contact entity bean.
 */
@Entity
@Table(name="be_contact")
@CompileStatic
class Contact extends BaseModel {
	/**
	 * Convenience Finder for 'active record' style.
	 */
	public static final Model.Finder<Long,Contact> find = new Model.Finder<Long,Contact>(Long, Contact)

	@Column(length=50)
	String firstName

	@Column(length=50)
	String lastName

	@Column(length=200)
	String email

	@Column(length=20)
	String phone

	@ManyToOne(optional=false)
	Customer customer
}
