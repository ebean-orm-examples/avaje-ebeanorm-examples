package org.example.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wjx
 *
 */
@Entity
@Table(name = "e_user")
public class User extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static Finder<UUID,User> find = new Finder<>(UUID.class, User.class);
	
	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", id=" + id
				+ ", creatorId=" + creatorId + ", creatorName=" + creatorName
				+ ", createTime=" + createTime + ", modifierId=" + modifierId
				+ ", modifierName=" + modifierName + ", modifyTime="
				+ modifyTime + "]";
	}
}
