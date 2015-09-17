package org.example.domain;

import com.avaje.ebean.PagedList;
import com.avaje.ebean.QueryEachConsumer;
import org.example.ExampleBaseTestCase;
import org.example.domain.query.QContact;
import org.example.domain.query.QCustomer;
import org.example.domain.query.QProduct;
import org.example.service.LoadExampleData;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ExamplePartialObjectQueryTest extends ExampleBaseTestCase {

  @Test
  public void test() {

    QContact con = QContact.alias();
    QCustomer cus = QCustomer.alias();
    QProduct pro = QProduct.alias();

    Customer customer =
        Customer.find.where()
            // tuning
            .select(cus.name, cus.registered)
            .orders.fetchAll()
            .orders.details.product.fetch(pro.name)
            .contacts.fetch(con.firstName)
            // predicates
            .id.eq(12)
            .findUnique();
  }

  @Test
  public void testSimpleQuery() {

    Country nz = Country.find.ref("NZ");

    PagedList<Customer> pagedList = new QCustomer()
        .id.greaterThan(12)
        .name.istartsWith("Rob")
        .billingAddress.country.equalTo(nz)
        .orderBy()
        .name.desc()
        .id.asc()
        .findPagedList(0, 100);

    pagedList.loadRowCount();
    List<Customer> list = pagedList.getList();
    int totalRowCount = pagedList.getTotalRowCount();

//
//    List<Customer> customers = new QCustomer()
//        .id.greaterThan(12)
//        .name.startsWith("Rob")
//        .billingAddress.country.equalTo(nz)
//        .orderBy()
//          .name.asc()
//        .findList();
  }

  @Test
  public void automaticallyAddJoins() {

    LoadExampleData.load();

//    Ebean.find(Customer.class).where()
//        .disjunction()
//          .gt("id", 1)
//          .conjunction()
//            .icontains("name", "jim")
//            .eq("inactive", false)
//          .endJunction()
//        .endJunction();

    Country nz = Country.find.ref("NZ");

    List<Customer> customers
        = new QCustomer()
        .billingAddress.city.equalTo("Auckland")
        .name.istartsWith("Rob")
        .version.between(1,100)
        .billingAddress.country.equalTo(nz)
        .registered.before(new Date())
        .or()
          .id.greaterThan(1)
          .and()
            .name.icontains("Jim")
            .inactive.isTrue()
          .endAnd()
        .endOr()
        .orderBy()
          .name.asc()
          .id.desc()
        .findList();

    //  where t1.city = ?
    //   and lower(t0.name) like ? escape''
    //   and t0.version between  ? and ?
    //   and t1.country_code = ?
    //   and t0.registered < ?
    // and (t0.id > ?  or (lower(t0.name) like ? escape''  and t0.inactive = ? ) ) ;
    //
    // --bind(Auckland,rob%,1,100,NZ,2015-09-16 06:58:26.777,1,%jim%,true)


    System.out.println(customers);

  }
}
