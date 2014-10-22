package org.example.service

import groovy.transform.CompileStatic
import org.example.domain.Customer

/**
 *
 */
@CompileStatic
class LoadCustomerService {


  void loadSome() {

    Customer rob = new Customer(name:"Rob")
    rob.save();

    Customer richard = new Customer(name:"Richard", registered:new Date())
    richard.save()

    richard.name = "was richard"
    richard.save();

    def customers = Customer.find.order().asc("id").findList();

    for (Customer customer: customers) {
      System.out.println("Hello $customer.name $customer.id")
    }


  }

}
