package org.example.module

import com.avaje.ebean.EbeanServer
import com.avaje.ebean.EbeanServerFactory
import com.avaje.ebean.config.ServerConfig
import com.google.inject.Provider
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Creates the default EbeanServer instance.
 *
 * This should be a singleton.
 */
public class EbeanServerProvider : Provider<EbeanServer> {

  override fun get(): EbeanServer? {

    val config = ServerConfig()
    config.setName("h2")

    // need to set defaultServer to true so that
    // this is the same instance used via Model
    config.setDefaultServer(true)

    // can pass in Properties or just load them
    // via ebean.properties
    config.loadFromProperties()

    return EbeanServerFactory.create(config);
  }
}