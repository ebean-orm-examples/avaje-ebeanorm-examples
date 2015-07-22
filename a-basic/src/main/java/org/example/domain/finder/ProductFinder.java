package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Product;

/**
 * Add finder methods here.
 */
public class ProductFinder extends Model.Finder<Long,Product> {

  public ProductFinder() {
    super(Product.class);
  }

  /**
   * Find by SKU.
   */
  public Product bySku(String sku) {
    return where().eq("sku", sku).findUnique();
  }
}
