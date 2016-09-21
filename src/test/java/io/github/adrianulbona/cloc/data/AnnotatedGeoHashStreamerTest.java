package io.github.adrianulbona.cloc.data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class AnnotatedGeoHashStreamerTest {

	@Test
	public void testNotNull() throws Exception {
		assertNotNull(new AnnotatedGeoHashStreamer().stream());
	}

	@Test
	public void testCount() throws Exception {
		assertEquals(1195623, new AnnotatedGeoHashStreamer().stream().count());
	}

	@Test
	public void testValidCountry() throws Exception {
		new AnnotatedGeoHashStreamer().stream().forEach(annotatedGeoHash -> {
			assertFalse(annotatedGeoHash.getAnnotations().length == 0);
		});
	}

	@Test
	public void testValidGeoHash() throws Exception {
		new AnnotatedGeoHashStreamer().stream().forEach(annotatedGeoHash -> {
			assertFalse(annotatedGeoHash.getGeoHash().isEmpty());
		});
	}
}