package com.alpha.cache;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ExpiryCacheTest {

	ExpiryCache<String, String> cache;
	
	@Before
	public void setUp() throws Exception {
		cache = new ExpiryCacheImpl<String, String>();
	}

	/*@Test
	public void testExpiryCacheImpl() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testPut() {
		cache.put("1", "value1", 1000, TimeUnit.MILLISECONDS);
		for(int i=0; i<100; i++){
			cache.get("1");
		}
	}
/*
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}*/
	
}
