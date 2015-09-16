package org.example.service

import com.avaje.ebean.annotation.Transactional
import org.example.domain.Customer
import org.example.domain.Product

/**
 * Uses @Transactional and byte code enhancement.
 */
public class WithTransactional {

  @Transactional
  fun performInTransaction() {

    val custJim = Customer()
    custJim.name = "ShouldNotFindMe"
    custJim.save()

    val prod = Product()
    prod.sku = "78G"
    prod.name = "Bulldozer"
    prod.save()

    throw RuntimeException("Intentional")
  }
}
