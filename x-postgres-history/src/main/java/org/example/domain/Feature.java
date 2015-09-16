package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Feature entity bean.
 */
@Entity
@Table(name = "feature")
public class Feature extends BaseModel {

  @Size(max = 60)
  String name;

  String notes;

  @ManyToMany
  @JoinTable(name = "customer_feature")
  List<Customer> customers;

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

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
