package com.alpha.cache.queue;

import com.alpha.cache.TTLEntry;

public interface RemovalQueue<K, V> {

	void putEntry(TTLEntry<K, V> e);
	void removeEntry(TTLEntry<K, V> e);
	void removeEntries();
}
