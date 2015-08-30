package main;

import org.avaje.ebean.typequery.generator.Generator;
import org.avaje.ebean.typequery.generator.GeneratorConfig;

import java.io.IOException;

/**
 * Generate type query beans for each entity bean.
 */
public class MainTypeBeanGenerator {

  public static void main(String[] args) throws IOException {


    GeneratorConfig config = new GeneratorConfig();
    //config.setClassesDirectory("./target/test-classes");
    //config.setDestDirectory("./src/test/java");
    //config.setDestResourceDirectory("./src/test/resources");

    config.setEntityBeanPackage("org.example.domain");
    //config.setDestPackage("org.example.domain.query");
    //config.setMaxPathTraversalDepth(3);
    //config.setAopStyle(false);

    //config.setOverwriteExistingFinders(true);

    Generator generator = new Generator(config);
    generator.generateQueryBeans();
    //generator.generateFinders();
    //generator.modifyEntityBeansAddFinderField();

  }
}
