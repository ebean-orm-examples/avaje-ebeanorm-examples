package org.example.domain;

import java.util.ArrayList;

import javax.validation.constraints.AssertTrue;
import javax.xml.ws.Action;
import javax.xml.ws.soap.Addressing;

@Addressing
public class SomePojo {

  @AssertTrue
  String name;

  ArrayList<String> foos = new ArrayList<>();
  
  @Action
  public String getName() {
    
//    foos.forEach(val -> {
//      System.out.println("hello "+val);
//    });
    
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
