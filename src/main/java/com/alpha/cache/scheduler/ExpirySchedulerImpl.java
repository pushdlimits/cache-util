package com.alpha.cache.scheduler;

import com.alpha.cache.TTLEntry;

public class ExpirySchedulerImpl<K,V> implements ExpiryScheduler<K, V>{

	@Override
	public void scheduleExpiry(TTLEntry<K, V> e) {
		// TODO Auto-generated method stub
		
	}
	
	//private void schedule(){
		 //reschedule the scheduler for the next closest expiry time
	//}

}
