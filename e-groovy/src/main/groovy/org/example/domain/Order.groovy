package org.example.domain

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Order entity bean.
 */
@Entity
@Table(name = "o_order")
@CompileStatic
class Order extends BaseModel {
	public enum Status {
		NEW, APPROVED, SHIPPED, COMPLETE
	}

	Status status

	java.sql.Date orderDate

	java.sql.Date shipDate

	@NotNull
	@ManyToOne
	Customer customer

	@ManyToOne
	Address shippingAddress

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	@javax.persistence.OrderBy("id asc")
	List<OrderDetail> details

	public String toString() {
		return "$id $status: $status, $customer: $customer"
	}

	/**
	 * Set the customer with their current shipping address.
	 */
	public void setCustomerWithShipping(Customer customer) {
		this.customer = customer
		this.shippingAddress = customer.shippingAddress
	}

	public void addDetail(OrderDetail detail) {
		if (details == null) {
			details = []
		}

		details.add(detail)
	}
}
