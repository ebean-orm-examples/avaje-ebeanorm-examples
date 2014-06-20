package org.example.domain;

import com.avaje.ebean.event.BeanPersistAdapter;
import com.avaje.ebean.event.BeanPersistRequest;

/**
 * Example which sets user id and name on insert and update of beans
 * that extend AbstractModel.
 * 
 * @author wjx
 */
public class BusinessBeanPersistAdapter extends BeanPersistAdapter {

	@Override
	public boolean isRegisterFor(Class<?> cls) {
		return AbstractModel.class.isAssignableFrom(cls);
	}

	@Override
	public boolean preInsert(BeanPersistRequest<?> request) {
	  
		Object bean = request.getBean();
		AbstractModel model = (AbstractModel) bean;

		// Typically get user details from a ThreadLocal security context
		String userId = "CreatorId";
		String userName = "CreatorName";
		
		model.setCreatorId(userId);
		model.setCreatorName(userName);
		model.setModifierId(userId);
		model.setModifierName(userName);
		
		return super.preInsert(request);
	}

	@Override
	public boolean preUpdate(BeanPersistRequest<?> request) {

	  Object bean = request.getBean();
		AbstractModel model = (AbstractModel) bean;
		
    // Typically get user details from a ThreadLocal security context
		String userId = "ModifierId";
		String userName = "ModifierName";

		model.setModifierId(userId);
		model.setModifierName(userName);
		
		return super.preUpdate(request);
	}
}
