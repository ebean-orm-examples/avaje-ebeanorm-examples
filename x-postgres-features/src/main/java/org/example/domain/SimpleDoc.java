package org.example.domain;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Map;

@Entity
@Table(name="p_doc")
public class SimpleDoc extends Model {

  public static Find<Long,SimpleDoc> find = new Find<Long,SimpleDoc>(){};

  @Id
  Long id;

  @Version
  Long version;

  String name;

  @DbJsonB
  Map<String,Object> content;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String,Object> getContent() {
    return content;
  }

  public void setContent(Map<String,Object> content) {
    this.content = content;
  }
}
