package io.github.adrianulbona.cloc;

import org.junit.Test;

import static io.github.adrianulbona.cloc.GeoDeliveryStreamer.*;
import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoDeliveryStreamerTest {

	@Test
	public void testNotNull() throws Exception {
		assertNotNull(new GeoDeliveryStreamer().with(Granularity.SIX));
	}

	@Test
	public void testCount() throws Exception {
		assertEquals(9505218, new GeoDeliveryStreamer().with(Granularity.SIX).count());
	}

	@Test
	public void testValidCountry() throws Exception {
		new GeoDeliveryStreamer().with(Granularity.SIX).forEach(geoHashCountry -> {
			assertNotNull(geoHashCountry.getPack());
		});
	}

	@Test
	public void testValidGeoHash() throws Exception {
		new GeoDeliveryStreamer().with(Granularity.SIX).forEach(geoHashCountry -> {
			assertNotNull(geoHashCountry.getPath());
			assertFalse(geoHashCountry.getPath().isEmpty());
		});
	}
}