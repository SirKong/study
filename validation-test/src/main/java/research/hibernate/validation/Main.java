package research.hibernate.validation;

import research.hibernate.validation.extend.ExtendEntity;
import research.hibernate.validation.simple.SimpleEntity;

public class Main {

	public static void main(String[] args) {
		SimpleEntity simpleEntity = new SimpleEntity();
		simpleEntity.setPassword("123346");
		System.out.println(ValidationUtils.validateEntity(simpleEntity));
		
		
		ExtendEntity ee = new ExtendEntity();
	    ee.setPassword("212");
	    ValidationResult result = ValidationUtils.validateEntity(ee);
	    System.out.println("--------------------------");
	    System.out.println(result);
	}
}
