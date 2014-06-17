package org.example.domain

import com.avaje.ebean.Model
import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Customer entity bean.
 */
@Entity
@Table(name="be_customer")
@CompileStatic
class Customer extends BaseModel {
	/**
	 * Convenience Finder for 'active record' style.
	 */
	public static final Model.Finder<Long,Customer> find = new Model.Finder<Long,Customer>(Long, Customer)

	boolean inactive

	@Column(length=100)
	String name

	Date registered

	@Column(length=1000)
	String comments

	@ManyToOne(cascade=CascadeType.ALL)
	Address billingAddress

	@ManyToOne(cascade=CascadeType.ALL)
	Address shippingAddress

	@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
	List<Contact> contacts

	/**
	 * Helper method to add a contact to the customer.
	 */
	public void addContact(Contact contact) {
		if (contacts == null) {
			contacts = []
		}
		// setting the customer is automatically done when Ebean does
		// a cascade save from customer to contacts.
		contact.customer = this

		contacts.add(contact);
	}
}
