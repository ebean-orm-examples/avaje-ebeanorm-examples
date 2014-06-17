package org.avaje.ebean.groovy

import com.avaje.ebean.EbeanServer
import com.avaje.ebean.ExpressionList
import com.avaje.ebean.QueryIterator
import com.avaje.ebean.TxRunnable
import groovy.transform.CompileStatic

/**
 * This is just to demonstrate extensions on static classes. You cannot use this in the classes in the <b>same</b>
 * project, separate it into a separate artifact if you are wanting to use these features (github.com/uoa-group-applications/common-ebean)
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 */
@CompileStatic
class EbeanExtensionModule {
	/**
	 * As an iterator is a resource and *must* be closed, we use this mechanism to allow for a clean
	 * cleaning up
	 *
	 * @param expressionList
	 * @param iteratorClosure
	 */
	public static eachEntity(ExpressionList expressionList, Closure iteratorClosure) {
		QueryIterator queryIterator = expressionList.findIterate()

		try {
			while (queryIterator.hasNext()) {
				iteratorClosure.call(queryIterator.next())
			}
		} finally {
			if (queryIterator != null) {
				queryIterator.close()
			}
		}
	}

	/**
	 * This kind of iterator expects the closure will return a boolean value, that if false, will cause the iterator
	 * to exit.
	 *
	 * @param expressionList
	 * @param iteratorClosure
	 */
	public static eachEntityBoolean(ExpressionList expressionList, Closure iteratorClosure) {
		QueryIterator queryIterator = expressionList.findIterate()

		boolean keepGoing = true

		try {
			while (keepGoing && queryIterator.hasNext()) {
				keepGoing = iteratorClosure.call(queryIterator.next())
			}
		} finally {
			if (queryIterator != null) {
				queryIterator.close()
			}
		}
	}

	/**
	 * This is for demonstration only. You may not use txRunnables.
	 *
	 * @param server
	 * @param txRunnable
	 * @return
	 */
	public static run(EbeanServer server, Closure txRunnable) {
		server.execute(new TxRunnable() {
			@Override
			void run() {
				txRunnable.call()
			}
		})
	}
}
