package org.example.domain.excludem2m;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.History;
import com.avaje.ebean.annotation.HistoryExclude;
import org.example.domain.Address;
import org.example.domain.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Test Customer that @HistoryExclude on the features ManyToMany property.
 */
@History
@Entity
@Table(name="customer")
public class CustomerExcludeM2M extends BaseModel {

  public static final Model.Finder<Long,CustomerExcludeM2M> find = new Model.Finder<>(CustomerExcludeM2M.class);

  @Size(max = 100)
  String name;

  LocalDate registered;

  @Size(max = 1000)
  String comments;

  @ManyToOne(cascade=CascadeType.ALL)
  Address billingAddress;

  @ManyToOne(cascade=CascadeType.ALL)
  Address shippingAddress;

  @OneToMany(mappedBy="customer")
  List<ContactExcludeM2M> contacts;

  /**
   * HistoryExclude on ManyToMany means the intersection table does not have history.
   */
  @HistoryExclude
  @ManyToMany
  List<FeatureExcludeM2M> features;


  public String toString() {
    return "id"+id+" name:"+name+" comments:"+comments;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDate registered) {
    this.registered = registered;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public List<ContactExcludeM2M> getContacts() {
    return contacts;
  }

  public void setContacts(List<ContactExcludeM2M> contacts) {
    this.contacts = contacts;
  }

  public List<FeatureExcludeM2M> getFeatures() {
    return features;
  }

  public void setFeatures(List<FeatureExcludeM2M> features) {
    this.features = features;
  }
}
