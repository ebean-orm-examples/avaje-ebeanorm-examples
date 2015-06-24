package org.example.extension

import org.slf4j.LoggerFactory

/**
 * Helper function to create a Logger.
 */
fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)
