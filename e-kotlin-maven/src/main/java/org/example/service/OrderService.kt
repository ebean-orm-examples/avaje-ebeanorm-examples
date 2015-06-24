package org.example.service

import com.avaje.ebean.EbeanServer
import com.avaje.ebean.annotation.Transactional
import org.example.domain.Order
import org.example.extension.loggerFor
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 */
@Singleton
public class OrderService {

  private val log = loggerFor(javaClass)

  private val server : EbeanServer

  @Inject
  constructor(server : EbeanServer){
    this.server = server
  }

  @Transactional
  fun save(order: Order) {

    val ebeanServer = Order.db()

    if (ebeanServer != server) {
      log.error("not the same server instance!!")
    }

    log.debug("saving order")
    order.save()

    log.debug("server ${ebeanServer.getName()}")
  }
}