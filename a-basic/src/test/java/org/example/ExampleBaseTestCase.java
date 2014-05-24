package org.example;

import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Use to dynamically load the avaje-ebeanorm-agent.
 * 
 * Alternatives: 
 * - the IntelliJ or Eclipse plugins, 
 * - rely on maven/ant enhancement
 * - specify the java agent on the command line
 *
 */
public class ExampleBaseTestCase {

  protected static Logger logger = LoggerFactory.getLogger(ExampleBaseTestCase.class);
  
  static {
    logger.debug("... preStart");
    if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent","debug=1;packages=org.example.**,com.avaje.ebean.*")) {
      logger.info("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
    }    
  }
}
