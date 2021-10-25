package centripio.ecommerce.entity;

import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

public class EntityAudit {
	@PreUpdate
	private void preUpdate(Object obj) {
		System.out.println("Pre Update");
	}
	
	@PostUpdate
	private void postUpdate(Object obj) {
		System.out.println("postUpdate");
	}
}
