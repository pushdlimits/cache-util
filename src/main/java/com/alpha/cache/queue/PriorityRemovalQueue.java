package com.alpha.cache.queue;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

import com.alpha.cache.TTLEntry;

/**
 * A queue to expire entries using a priority queue
 * 
 * @author saurabh
 * 
 * @param <K>
 * @param <V>
 */
public class PriorityRemovalQueue<K, V> implements RemovalQueue<K, V> {

	private PriorityBlockingQueue<TTLEntry<K, V>> queue;
	private Map<K, TTLEntry<K, V>> map;

	public PriorityRemovalQueue(ConcurrentMap<K, TTLEntry<K, V>> map) {
		this.map = map;
	}

	@Override
	public void putEntry(TTLEntry<K, V> e) {
		queue.put(e);
	}

	@Override
	public void removeEntry(TTLEntry<K, V> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEntries() {
		try {
			while (!queue.isEmpty()
					&& (queue.peek().getExpiryTime() < System.nanoTime())) {
				TTLEntry<K, V> e = queue.poll();
				map.remove(e.getKey());
			}
		} catch (NullPointerException e) {
			// Catching exception if an entry is already removed
		}
	}

	public static class TTLEntryComparator<K, V> implements
			Comparator<TTLEntry<K, V>> {
		@Override
		public int compare(TTLEntry<K, V> e1, TTLEntry<K, V> e2) {
			long t1 = e1.getExpiryTime(), t2 = e2.getExpiryTime();
			return (t1 > t2) ? 1 : (t1 < t2) ? -1 : 0;
		}
	}

}
