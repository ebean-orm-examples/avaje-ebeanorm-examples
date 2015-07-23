package org.example.domain;

import javax.validation.constraints.AssertTrue;
import javax.xml.ws.Action;
import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;

@Addressing
public class SomePojo {

  @AssertTrue
  String name;

  ArrayList<String> foos = new ArrayList<>();
  
  @Action
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
