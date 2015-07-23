package org.avaje.ebean.typequery;

import com.avaje.ebean.ExpressionList;

public interface ExpressionOwner {

  ExpressionList<?> expr();
}
