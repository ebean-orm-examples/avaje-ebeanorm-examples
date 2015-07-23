package org.querytest;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import org.example.domain.Contact;
import org.example.domain.Customer;
import org.example.domain.typequery.QContact;
import org.example.domain.typequery.QCustomer;
import org.junit.Test;

import java.util.List;

public class QCustomerTest {

  @Test
  public void testQuery() {

    Ebean.getDefaultServer();

    List<Contact> contacts = new QContact()
        .email.like("asd")
        .notes.title.like("asd")
        //.orderBy().
        .id.desc()
        .findList();

    Customer customer =
        new QCustomer()
          .id.eq(1234)
          .name.like("asd")
          .contacts.email.like("%@foo.com")
          .contacts.notes.id.gt(123)
            //.or()
          .findUnique();

    List<Customer> customers
        = new QCustomer()
          .name.like("asd")
          .findList();

//    QCustomer c = QCustomer.I;
//    ExpressionList<Customer> expr = new QCustomer().expr();
//    expr.eq(c.contacts.email, 123);
  }
}
