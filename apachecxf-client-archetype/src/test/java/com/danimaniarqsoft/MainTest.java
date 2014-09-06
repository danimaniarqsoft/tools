package com.danimaniarqsoft;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest extends TestCase {
	private static final Logger LOG = LoggerFactory.getLogger(MainTest.class);

	@Test
	private void public_void() {
		LOG.info("Runnig test");
	}
}
