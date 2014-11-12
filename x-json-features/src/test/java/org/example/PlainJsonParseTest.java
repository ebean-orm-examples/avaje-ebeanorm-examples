package org.example;

import java.util.Map;

import org.boon.json.JsonParser;
import org.boon.json.JsonParserAndMapper;
import org.boon.json.JsonParserFactory;
import org.junit.Test;

public class PlainJsonParseTest {

  @Test
  public void test() {
    
    final JsonParser parser = new JsonParserFactory().create ();
    
    JsonParserAndMapper json = new JsonParserFactory().create();

    String jsonString = "{\"length\":\"100\", \"height\":200}";

    Map object = json.parse(Map.class, jsonString);
    
    //Object object = parser.parse(json);
    
    Map<String, Object> map = EJson.parseObject(jsonString);
    System.out.println(map);
    
    String mapAsJson = EJson.write(map);
    System.out.println(mapAsJson);
    
  }
}
