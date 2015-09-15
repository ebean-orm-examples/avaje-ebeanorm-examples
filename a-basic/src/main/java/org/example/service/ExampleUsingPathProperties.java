package org.example.service;

import java.util.List;

import org.example.domain.Contact;
import org.example.domain.Customer;
import org.example.domain.Order;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.text.PathProperties;
import com.avaje.ebean.text.json.JsonContext;

public class ExampleUsingPathProperties extends LoadAgentAtRuntime {

  public static void main(String[] args) {
    
//    new ExampleUsingPathProperties().runExample();
    new ExampleUsingPathProperties().runExampleUsingOrders();
  }
  
  public void runExample() {

    

    LoadExampleData.load();
    
    List<Customer> list2 = Customer.find
        .query()
      .select("name")
      .fetch("contacts")
      .findList();
    

    PathProperties pathProperties = PathProperties.parse("(id,version,name,contacts(id,email,version))");

    
    Query<Customer> query = Customer.find.query();
    pathProperties.apply(query);
    
    List<Customer> list = query.findList();
    
    JsonContext jsonContext = Customer.find.db().json();
    
    String jsonString = jsonContext.toJson(list);
    System.out.println(jsonString);

    
    for (Customer customer : list2) {
      customer.getName();
      List<Contact> contacts = customer.getContacts();
      if (contacts != null) {
        for (Contact contact : contacts) {
          System.out.println("contact: "+contact.getFirstName()+" "+contact.getLastName());
        }
      }
      
    }
  }
  
  public void runExampleUsingOrders() {
    
    LoadExampleData.load();
    
    PathProperties pathProperties = PathProperties.parse("(id,status,orderDate,customer(id,name),details(*,product(sku)))");
    
    Query<Order> query = Ebean.createQuery(Order.class);
    pathProperties.apply(query);
    
    List<Order> orders = query.where().gt("id", 1).findList();

    String rawJson = Ebean.json().toJson(orders);
    System.out.println(rawJson);
  }
  
}
