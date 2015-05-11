package org.example.domain.type;


import com.avaje.ebeaninternal.server.type.DataBind;
import com.avaje.ebeaninternal.server.type.ScalarTypeBaseVarchar;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

public class ScalarTypeJsonNodePostgres extends ScalarTypeBaseVarchar<JsonNode> {

  public ScalarTypeJsonNodePostgres() {
    this(DBTYPE_JSON, new ObjectMapper());
  }

  /**
   * Postgres DB type JSON.
   */
  private static final String DBTYPE_JSON = "json";

  /**
   * Postgres DB type JSONB.
   */
  private static final String DBTYPE_JSONB = "jsonb";

  final ObjectMapper objectMapper;

  final String dbType;

  protected ScalarTypeJsonNodePostgres(String dbType, ObjectMapper objectMapper) {
    super(JsonNode.class, false, Types.VARCHAR);
    this.objectMapper = objectMapper;
    this.dbType = dbType;
  }

  @Override
  public void bind(DataBind b, JsonNode value) throws SQLException {

    String rawJson = (value == null) ? null : formatValue(value);

    PGobject pgo = new PGobject();
    pgo.setType(dbType);
    pgo.setValue(rawJson);
    b.setObject(pgo);
  }

  @Override
  public JsonNode jsonRead(JsonParser ctx, JsonToken event) throws IOException {

    return objectMapper.readValue(ctx, JsonNode.class);
  }

  @Override
  public void jsonWrite(JsonGenerator ctx, String name, JsonNode value) throws IOException {

    ctx.writeFieldName(name);
    objectMapper.writeTree(ctx, value);
  }

  @Override
  public String formatValue(JsonNode v) {
    return v.toString();
  }

  @Override
  public JsonNode parse(String value) {

    try {
      return objectMapper.readTree(value);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public JsonNode convertFromDbString(String dbValue) {
    return parse(dbValue);
  }

  @Override
  public String convertToDbString(JsonNode beanValue) {
    return formatValue(beanValue);
  }
}
