package org.example;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.SimpleDoc;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimpleDocTest extends ExampleBaseTestCase {

  @Test
  public void test() throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    String rawJson = "{\"docName\":\"rob doc\", \"docScore\":234}";

    JsonNode jsonNode = mapper.readTree(rawJson);

    SimpleDoc doc = new SimpleDoc();
    doc.setName("doc1");
    doc.setContent(jsonNode);

    Ebean.save(doc);


    String fullJson = Ebean.json().toJson(doc);

    SimpleDoc doc2 = Ebean.json().toBean(SimpleDoc.class, fullJson);

    assertEquals(doc.getId(), doc2.getId());
    assertEquals(doc.getName(), doc2.getName());
    assertEquals(doc.getVersion(), doc2.getVersion());
    assertEquals(doc.getContent().toString(), doc2.getContent().toString());

    SimpleDoc doc3 = Ebean.find(SimpleDoc.class, doc.getId());
    assertEquals(doc.getId(), doc3.getId());
    assertEquals(doc.getName(), doc3.getName());
    assertEquals(doc.getVersion(), doc3.getVersion());
    assertEquals(doc.getContent().toString(), doc3.getContent().toString());

  }

  @Test
  public void testNullValue() throws IOException {


    SimpleDoc doc = new SimpleDoc();
    doc.setName("doc1");

    Ebean.save(doc);


    String fullJson = Ebean.json().toJson(doc);

    SimpleDoc doc2 = Ebean.json().toBean(SimpleDoc.class, fullJson);

    assertEquals(doc.getId(), doc2.getId());
    assertEquals(doc.getName(), doc2.getName());
    assertEquals(doc.getVersion(), doc2.getVersion());
    assertNull(doc2.getContent());


    SimpleDoc doc3 = Ebean.find(SimpleDoc.class, doc.getId());
    assertEquals(doc.getId(), doc3.getId());
    assertEquals(doc.getName(), doc3.getName());
    assertEquals(doc.getVersion(), doc3.getVersion());
    assertNull(doc3.getContent());

  }
}
