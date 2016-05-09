package com.ccnu.test.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionIf {
	OptionItem[] optionItems();

	public @interface OptionItem {

		String condition();

		String[] options();
	}
}