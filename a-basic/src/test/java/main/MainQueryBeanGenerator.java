package main;

import org.avaje.ebean.typequery.generator.Generator;
import org.avaje.ebean.typequery.generator.GeneratorConfig;

import java.io.IOException;

/**
 * Generate type query beans for each entity bean.
 */
public class MainQueryBeanGenerator {

  public static void main(String[] args) throws IOException {

    GeneratorConfig config = new GeneratorConfig();
    //config.setClassesDirectory("./target/classes");
    //config.setDestDirectory("./src/main/java");
    //config.setDestResourceDirectory("./src/main/resources");

    config.setEntityBeanPackage("org.example.domain");
    //config.setDestPackage("org.example.domain.query");


    Generator generator = new Generator(config);
    generator.generateQueryBeans();
    //generator.generateFinders();
    //generator.modifyEntityBeansAddFinderField();

  }
}
