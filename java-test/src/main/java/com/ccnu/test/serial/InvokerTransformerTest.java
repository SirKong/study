package com.ccnu.test.serial;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;

public class InvokerTransformerTest {
	public static void main(String[] args) {
		Transformer transform = new InvokerTransformer("append",
				new Class[] { String.class }, new Object[] { "exploitcat?" });
		Object newObject = transform
				.transform(new StringBuffer("your name is "));
		System.out.println(newObject);
	}
}
