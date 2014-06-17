package org.avaje.ebean.groovy

import com.avaje.ebean.Ebean
import com.avaje.ebean.TxRunnable
import groovy.transform.CompileStatic

/**
 * This is just to demonstrate extensions on static classes. You cannot use this in the classes in the <b>same</b>
 * project, separate it into a separate artifact if you are wanting to use these features (github.com/uoa-group-applications/common-ebean)
 *
 * @author: Richard Vowles - https://plus.google.com/+RichardVowles
 */
@CompileStatic
class StaticEbeanExtensionModule {

	public static run(Ebean server, Closure txRunnable) {
		server.execute(new TxRunnable() {
			@Override
			void run() {
				txRunnable.call()
			}
		})
	}
}
