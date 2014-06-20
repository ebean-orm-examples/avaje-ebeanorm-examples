package org.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Test the use of BusinessBeanPersistAdapter to set additional user id and name properties on
 * preInsert and preUpdate.
 */
public class UserTest extends ExampleBaseTestCase {

  @Test
  public void testBeanPersist() throws Exception {

    // ... the BusinessBeanPersistAdapter will set the
    // created user id and name onto the bean
    User user = new User();
    user.setName("Test");
    user.setAge(20);
    user.save();

    assertNotNull(user.getId());
    assertEquals("CreatorId", user.getCreatorId());
    assertEquals("CreatorName", user.getCreatorName());

    // stateless update ... the BusinessBeanPersistAdapter will
    // set the modified user id and name onto the bean
    User user2 = new User();
    user2.setId(user.getId());
    user2.setName("Test2");
    user2.update();

    // check that all the values are expected with the
    // appropriate values set by BusinessBeanPersistAdapter
    User user3 = User.find.byId(user.getId());

    assertEquals("CreatorId", user3.getCreatorId());
    assertEquals("CreatorName", user3.getCreatorName());
    assertEquals("ModifierId", user3.getModifierId());
    assertEquals("ModifierName", user3.getModifierName());

  }

}
