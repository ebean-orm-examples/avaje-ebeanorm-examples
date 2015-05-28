package org.example;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.SimpleDocUsingJsonNode;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimpleDocWithJsonNodeTest extends ExampleBaseTestCase {

  @Test
  public void test() throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    String rawJson = "{\"docName\":\"rob doc\", \"docScore\":234}";

    JsonNode jsonNode = mapper.readTree(rawJson);

    SimpleDocUsingJsonNode doc = new SimpleDocUsingJsonNode();
    doc.setName("doc1");
    doc.setContent(jsonNode);

    doc.save();


    String fullJson = Ebean.json().toJson(doc);

    SimpleDocUsingJsonNode doc2 = Ebean.json().toBean(SimpleDocUsingJsonNode.class, fullJson);

    assertEquals(doc.getId(), doc2.getId());
    assertEquals(doc.getName(), doc2.getName());
    assertEquals(doc.getVersion(), doc2.getVersion());
    assertEquals(doc.getContent().toString(), doc2.getContent().toString());

    SimpleDocUsingJsonNode doc3 = Ebean.find(SimpleDocUsingJsonNode.class, doc.getId());
    assertEquals(doc.getId(), doc3.getId());
    assertEquals(doc.getName(), doc3.getName());
    assertEquals(doc.getVersion(), doc3.getVersion());
    assertEquals(doc.getContent().toString(), doc3.getContent().toString());

  }

  @Test
  public void testNullValue() throws IOException {


    SimpleDocUsingJsonNode doc = new SimpleDocUsingJsonNode();
    doc.setName("doc1");

    Ebean.save(doc);


    String fullJson = Ebean.json().toJson(doc);

    SimpleDocUsingJsonNode doc2 = Ebean.json().toBean(SimpleDocUsingJsonNode.class, fullJson);

    assertEquals(doc.getId(), doc2.getId());
    assertEquals(doc.getName(), doc2.getName());
    assertEquals(doc.getVersion(), doc2.getVersion());
    assertNull(doc2.getContent());


    SimpleDocUsingJsonNode doc3 = SimpleDocUsingJsonNode.find.byId(doc.getId());
    assertEquals(doc.getId(), doc3.getId());
    assertEquals(doc.getName(), doc3.getName());
    assertEquals(doc.getVersion(), doc3.getVersion());
    assertNull(doc3.getContent());

  }
}
