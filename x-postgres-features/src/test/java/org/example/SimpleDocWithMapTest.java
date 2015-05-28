package org.example;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.EJson;
import org.example.domain.SimpleDoc;
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


    String rawJson = "{\"docName\":\"rob doc\", \"docScore\":234, \"title\":\"Some title\"}";

    Map<String, Object> content = EJson.parseObject(rawJson);

    SimpleDoc doc = new SimpleDoc();
    doc.setName("doc1");
    doc.setContent(content);

    doc.save();

    SimpleDoc doc1 = SimpleDoc.find.byId(doc.getId());

    assertEquals(doc1.getName(), doc.getName());
    assertEquals("rob doc", doc1.getContent().get("docName"));

    List<SimpleDoc> docs = SimpleDoc.find.where()
        .raw("jsonbExists(content#>'{docName}', 'rob doc')")
        //.raw("content#>'{docName}' ? 'rob doc'")
        .findList();

    assertTrue(!docs.isEmpty());

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
