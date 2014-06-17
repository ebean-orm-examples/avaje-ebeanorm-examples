package org.example.domain

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Size

/**
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 * Product entity bean.
 */
@Entity
@Table(name = "o_product")
@CompileStatic
class Product extends BaseModel {
	@Size(max = 20)
	String sku

	String name
}
