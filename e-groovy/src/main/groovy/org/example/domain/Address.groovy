package org.example.domain

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Size

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Address entity bean.
 */
@Entity
@Table(name = "o_address")
@CompileStatic
class Address extends BaseModel {
	@Size(max = 100)
	String line1

	@Size(max = 100)
	String line2

	@Size(max = 100)
	String city

	@ManyToOne
	Country country

	/**
	 * Create a copy of the address. Used to provide a 'snapshot' of
	 * the shippingAddress for a give order.
	 *
	 * Does not copy inherited fields
	 */
	public Address createCopy() {
		return new Address(line1: line1, line2: line2, city: city, country: country)
	}

	public String toString() {
		return "$id $line1 $line2 $city $country"
	}
}
