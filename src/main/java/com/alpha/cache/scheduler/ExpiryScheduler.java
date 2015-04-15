package com.alpha.cache.scheduler;

import com.alpha.cache.TTLEntry;

/**
 * An eviction scheduler to automatically evict entries upon expiration
 * @author saurabh
 *
 * @param <K>
 * @param <V>
 */
public interface ExpiryScheduler<K, V> {

	public void scheduleExpiry(TTLEntry<K, V> e);

}
