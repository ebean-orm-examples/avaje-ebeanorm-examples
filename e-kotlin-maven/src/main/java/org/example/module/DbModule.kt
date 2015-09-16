package org.example.module

import com.avaje.ebean.EbeanServer
import com.google.inject.AbstractModule
import org.example.extension.loggerFor
import org.example.service.OrderService

/**
 *
 */
public class DbModule : AbstractModule() {

  private val log = loggerFor(javaClass)

  override fun configure() {

    log.debug("configure ...")

    bind(OrderService::class.java)
    bind(EbeanServer::class.java)
        .toProvider(EbeanServerProvider::class.java).asEagerSingleton()
  }
}