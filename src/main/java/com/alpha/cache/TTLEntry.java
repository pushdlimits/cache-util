package com.alpha.cache;

import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class TTLEntry<K, V> implements Entry<K, V> {

	private final K key;
	private V value;
	private long ttl;
	private long expiryTime;

	public TTLEntry(K k, V v, long ttl, TimeUnit timeUnit) {
		this.key = k;
		this.value = v;
		this.ttl = TimeUnit.NANOSECONDS.convert(ttl, timeUnit);
		this.expiryTime = ttl + System.nanoTime();
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	public long getExpiryTime() {
		return this.expiryTime;
	}

	@Override
	public V setValue(V v) {
		if (v == null) {
			throw new NullPointerException();
		} else {
			V old = this.value;
			this.value = v;
			return old;
		}
	}

	public boolean isExpired() {
		return System.nanoTime() > expiryTime ? true : false;
	}

	@Override
	public String toString() {
		return "TTLEntry [key=" + key + ", value=" + value + ", ttl=" + ttl
				+ ", expiryTime=" + expiryTime + "]";
	}

}
