package com.ccnu.test;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 本地内存缓存，支持ttl（Time To Live），单位ms；支持LRU（Least Recently
 * Used）；支持缓存大小限制item条数限制或者数据字节限制（通过设置transcoder属性）。
 * 
 * @author wukq14280@hundsun.com created at 2015年7月15日T下午5:29:57
 *
 */
public class LocalCache {

	/**
	 * 
	 * When transcoder is null,this property means item numbers of
	 * cache,otherwise it means bytes num.
	 */
	private int size;
	/**
	 * in mills
	 */
	private long ttl;
	private final LinkedHashMap<Object, ManagedItem<Object>> store;
	private String name;

	/**
	 * 
	 * @param size
	 */
	public LocalCache(final int size) {
		this(size, -1);
	}

	/**
	 * 
	 * @param transcoder
	 * @param size
	 * @param ttlInMillis
	 */
	public LocalCache(final int size, final long ttlInMillis) {
		this.size = size;
		this.ttl = ttlInMillis;
//		Assert.isTrue(size > 0, "Cache size must bigger than zero.");
		store = new LinkedHashMap<Object, ManagedItem<Object>>(size / 2, 0.75f,
				true);
	}

	/**
	 * Removes all cache entries.
	 */
	public void clear() {
		synchronized (store) {
			store.clear();
		}
	}

	// @Override
	public void put(final Object key, final Object value) {
		synchronized (store) {
			ManagedItem<Object> item = new ManagedItem<Object>(value,
					System.currentTimeMillis());
			store.put(key, item);
			while (store.size() > size) {
				remove(store.keySet().iterator().next());
			}
		}
	}

	private ManagedItem<Object> remove(Object key) {
		ManagedItem<Object> removed = store.remove(key);
		return removed;
	}

	// @Override
	public void delete(final Object key) {
		synchronized (store) {
			remove(key);
		}
	}

	@SuppressWarnings("unchecked")
	// @Override
	public <T> T get(Object key) {
		synchronized (store) {
			final ManagedItem<Object> item = store.get(key);
			if (item == null) {
				return null;
			}
			Object value = item.getValue();
			if (ttl > -1
					&& System.currentTimeMillis() - item.insertionTime > ttl) {
				remove(key);
				return null;
			}
			return (T) value;
		}
	}

	// @Override
	public String getName() {
		return this.name;
	}

	// @Override
	@SuppressWarnings("unchecked")
	public <T> T getNativeCache() {
		return (T) this.store;
	}

	/**
	 * Determines if the given key is cached without "touching" this key.
	 *
	 * @param key
	 *            the key
	 * @return <code>true</code> if the given key is present in the underlying
	 *         map, otherwise <code>false</code>.
	 */
	public boolean containsKey(final Object key) {
		synchronized (store) {
			return store.containsKey(key);
		}
	}

	/**
	 * The list of all keys, whose order is the order in which its entries were
	 * last accessed, from least-recently accessed to most-recently.
	 *
	 * @return a new list.
	 */
	public List<Object> getKeys() {
		synchronized (store) {
			return new java.util.ArrayList<Object>(store.keySet());
		}
	}

	/**
	 * Stores a value with the timestamp this value was added to the cache.
	 *
	 * @param <T>
	 *            the type of the value
	 */
	private final class ManagedItem<T> {
		private final T value;
		private final long insertionTime;

		private ManagedItem(final T value, final long accessTime) {
			this.value = value;
			this.insertionTime = accessTime;
		}

		public T getValue() {
			return value;
		}

	}

	public void setName(String name) {
		this.name = name;
	}

}

