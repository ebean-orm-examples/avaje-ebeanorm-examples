package org.example.service

import com.avaje.ebean.annotation.Transactional
import com.google.inject.Guice
import org.example.domain.Order
import org.example.module.DbModule
import org.junit.Test
import sun.security.pkcs11.Secmod

/**
 *
 */
public class OrderServiceTest {

  @Transactional
  @Test
  fun save() {

    val injector = Guice.createInjector(DbModule())

    val orderService = injector.getInstance(OrderService::class.java)

    val order = Order()
    order.status = Order.Status.APPROVED
    orderService.save(order);
  }
}