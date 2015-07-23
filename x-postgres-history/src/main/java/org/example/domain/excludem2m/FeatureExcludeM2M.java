package org.example.domain.excludem2m;

import org.example.domain.BaseModel;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Feature entity bean that relates to CustomerExcludeM2M.
 */
@Entity
@Table(name = "feature")
public class FeatureExcludeM2M extends BaseModel {

  @Size(max = 60)
  String name;

  @ManyToMany
  @JoinTable(name = "customer_feature")
  List<CustomerExcludeM2M> customers;

  public String toString() {
    return name;
  }

  /**
   * Return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set name.
   */
  public void setName(String name) {
    this.name = name;
  }

  public List<CustomerExcludeM2M> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomerExcludeM2M> customers) {
    this.customers = customers;
  }
}
