package com.alpha.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of ExpiryCache based on {@link ConcurrentHashMap} with a
 * {@link PriorityBlockingQueue} for expiring the entries.
 * 
 * @author saurabh
 * 
 * @param <K>
 * @param <V>
 */
public class ExpiryCacheImpl<K, V> implements ExpiryCache<K, V> {

	private ConcurrentMap<K, TTLEntry<K, V>> map;

	public ExpiryCacheImpl() {
		map = new ConcurrentHashMap<K, TTLEntry<K, V>>();
	}

	@Override
	public void put(K key, V value, int ttl, TimeUnit timeUnit) {
		TTLEntry<K, V> e = new TTLEntry<K, V>(key, value, ttl, timeUnit);
		map.put(key, e);
	}

	@Override
	public V get(K key) {
		TTLEntry<K, V> e = map.get(key);
		if (e != null && !removeIfExpired(e)) {
			e.getValue();
		}
		return null;
	}

	private boolean removeIfExpired(TTLEntry<K, V> e) {
		boolean isExpired = e.isExpired();
		if (isExpired)
			map.remove(e.getKey(), e);
		return isExpired;
	}

}
