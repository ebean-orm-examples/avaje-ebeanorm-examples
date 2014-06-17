package org.example.domain

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Order Detail entity bean.
 */
@Entity
@Table(name = "o_order_detail")
@CompileStatic
class OrderDetail extends BaseModel {
	@ManyToOne
	Order order

	Integer orderQty

	Integer shipQty

	Double unitPrice

	@ManyToOne
	Product product
}
