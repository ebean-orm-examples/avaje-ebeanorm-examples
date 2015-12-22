package org.example;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.EJson;
import org.example.domain.SimpleDoc;
import org.example.domain.query.QSimpleDoc;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleDocWithMapTest extends ExampleBaseTestCase {

  /**
   * Postgres function used to bypass issue using jsonb ? operator.
   *
   * CREATE FUNCTION jsonbExists (jsonb, text) RETURNS bool AS $$ SELECT $1 ? $2; $$ LANGUAGE sql;
   */
  @Test
  public void test() throws IOException {


    String rawJson = "{\"docName\":\"rob doc\", \"path\":{\"inner\":\"so\", \"other\":34},\"docScore\":234, \"title\":\"Some title\"}";

    Map<String, Object> content = EJson.parseObject(rawJson);

    SimpleDoc doc = new SimpleDoc();
    doc.setName("doc1");
    doc.setContent(content);

    doc.save();
//
//    if (true) {
//      return;
//    }


//    List<SimpleDoc> title = new QSimpleDoc()
//        //.id.lessOrEqualTo(12L)
//        .content.jsonExists("title")
//        .content.jsonEqualTo("title", "Some title")
//        .content.jsonGreaterOrEqual("path.other", 34)
//        .findList();
//
//    System.out.println(title);

//    if (true) {
//      return;
//    }


    List<SimpleDoc> list = new QSimpleDoc().query()
        .where().jsonEqualTo("content", "title", "Some title")
        .jsonBetween("content", "path.other", 30, 34)
        .jsonEqualTo("content", "path.other", 34)
        .jsonExists("content", "path.other")
        .jsonGreaterOrEqual("content", "path.other", 34)
        .jsonGreaterThan("content", "path.other", 30)
        .findList();

    System.out.println(list);

//    List<SimpleDoc> list = new QSimpleDoc()
//        .raw("jsonbExists(content #> '{docName}', 'rob doc')")
//        .findList();
//
//    List<SimpleDoc> list2 = new QSimpleDoc()
//        .raw("jsonbExists(content #> '{path,inner}', 'so')")
//        .findList();
//
//    List<SimpleDoc> list3 = new QSimpleDoc()
//        .raw("jsonbExists(content #> '{path,other}', '34')")
//        .findList();
//
//    SimpleDoc doc1 = SimpleDoc.find.byId(doc.getId());
//
//    assertEquals(doc1.getName(), doc.getName());
//    assertEquals("rob doc", doc1.getContent().get("docName"));
//
//    List<SimpleDoc> docs = SimpleDoc.find.where()
//        .raw("jsonbExists(content#>'{docName}', 'rob doc')")
//        //.raw("content#>'{docName}' ? 'rob doc'")
//        .findList();
//
//    assertTrue(!docs.isEmpty());

  }

  @Test
  public void testNullValue() throws IOException {


    SimpleDoc doc = new SimpleDoc();
    doc.setName("doc1");
    doc.save();


    String fullJson = Ebean.json().toJson(doc);

    SimpleDoc doc2 = Ebean.json().toBean(SimpleDoc.class, fullJson);

    assertEquals(doc.getId(), doc2.getId());
    assertEquals(doc.getName(), doc2.getName());
    assertEquals(doc.getVersion(), doc2.getVersion());
    assertNull(doc2.getContent());


    SimpleDoc doc3 = SimpleDoc.find.byId(doc.getId());
    assertEquals(doc.getId(), doc3.getId());
    assertEquals(doc.getName(), doc3.getName());
    assertEquals(doc.getVersion(), doc3.getVersion());
    assertNull(doc3.getContent());

  }
}
