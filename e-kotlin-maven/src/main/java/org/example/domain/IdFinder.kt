package org.example.domain

import com.avaje.ebean.Model.Finder
import com.avaje.ebean.EbeanServer
import java.util.UUID

/**
 * Provides common convenience finder methods.
 */
abstract class IdFinder<I, T>(idClass: Class<I>, beanClass: Class<T>) {

  /**
   * Find beans by creating a query.
   */
  public val find: Finder<I, T> = Finder(idClass, beanClass);

  /**
   * Return a reference bean given the Id value.
   */
  public fun ref(id: I): T {
    return find.ref(id);
  }

  /**
   * Return the default EbeanServer.
   */
  public fun db(): EbeanServer {
    return find.db()
  }

  /**
   * Return an EbeanServer given its name.
   */
  public fun db(server: String): EbeanServer {
    return find.db(server)
  }

}

/**
 * Id finder for beans with an Id type of Long.
 */
abstract class LongIdFinder<T>(cls: Class<T>) : IdFinder<Long, T>(javaClass<Long>(), cls) {}

/**
 * Id finder for beans with an Id type of String.
 */
abstract class StringIdFinder<T>(cls: Class<T>) : IdFinder<String, T>(javaClass<String>(), cls) {}

/**
 * Id finder for beans with an Id type of UUID.
 */
abstract class UUIDIdFinder<T>(cls: Class<T>) : IdFinder<UUID, T>(javaClass<UUID>(), cls) {}