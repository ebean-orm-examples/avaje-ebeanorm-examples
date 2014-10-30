package org.example.service

import org.example.domain.Customer
import org.example.domain.Product
import com.avaje.ebean.annotation.Transactional

/**
 * Created by rob on 30/10/14.
 */
/**
 * Uses @Transactional
 */
public class WithAtTransactional {

    Transactional
    fun performInTransaction() {

        val custJim = Customer()
        custJim.name = "InTransaction"
        custJim.save()

        val prod = Product()
        prod.sku = "78G"
        prod.name = "Bulldozer"
        prod.save()

        throw RuntimeException("Intentional")
    }
}
