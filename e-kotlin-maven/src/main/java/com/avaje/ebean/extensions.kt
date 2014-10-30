package com.avaje.ebean


/**
 * Execute the query processing each bean (and associated object graph) one at a time.
 * <p>
 * This is used for processing large resultSets where you want to avoid holding all the queries beans in memory at once.
 * This uses a new persistence context per bean (and associated graph).
 */
fun<T> ExpressionList<T>.findEach(body: (T) -> Unit) {

  val queryIterator = this.findIterate()

  try {
    while (queryIterator.hasNext()) {
      body(queryIterator.next())
    }
  } finally {
    // close the associated JDBC resources
    queryIterator?.close()
  }
}

/**
 * Execute the query processing each bean (and associated object graph) one at a time until the closure returns false
 * (or there are no more beans to process).
 * <p>
 * This is used for processing large resultSets where you want to avoid holding all the queries beans in memory at once.
 * This uses a new persistence context per bean (and associated graph).
 */
fun<T> ExpressionList<T>.findEachWhile(body: (T) -> Boolean) {

  val queryIterator = this.findIterate()

  try {
    while (queryIterator.hasNext()) {
      val bean = queryIterator.next()
      if (!body(bean)) {
        break;
      }
    }
  } finally {
    // close the associated JDBC resources
    queryIterator?.close()
  }
}