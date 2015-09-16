package org.example.domain

import org.junit.Test
import java.util.ArrayList

/**
 * Created by rob on 23/10/14.
 */
class SimpleTest {

  @Test
  fun simple() {

    var details: MutableList<OrderDetail> = ArrayList();
    details.add(OrderDetail())

    // Mutable ... subList, iterator etc
    val mutIterator = details.iterator()
    while (mutIterator.hasNext()) {
      mutIterator.next()
      mutIterator.remove()
    }


    // Immutable ... subList, iterator etc
    val someStrings = listOf("one", "two");
    //val subList = someStrings.subList(0, 1);

    val it = someStrings.iterator();
    while (it.hasNext()) {
      it.next();
      //it.remove();
    }
  }
}