package org.example.domain.finder;

import com.avaje.ebean.Finder;
import org.example.domain.Product;
import org.example.domain.query.QProduct;

public class ProductFinder extends Finder<Long,Product> {

  /**
   * Construct using the default EbeanServer.
   */
  public ProductFinder() {
    super(Product.class);
  }

  /**
   * Construct with a given EbeanServer.
   */
  public ProductFinder(String serverName) {
    super(Product.class, serverName);
  }

  /**
   * Start a new typed query.
   */
  protected QProduct where() {
     return new QProduct();
  }
}
