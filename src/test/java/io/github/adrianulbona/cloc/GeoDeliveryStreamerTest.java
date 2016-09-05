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
		assertNotNull(new GeoDeliveryStreamer().with(Granularity.FIVE));
	}

	@Test
	public void testCount() throws Exception {
		assertEquals(1259238, new GeoDeliveryStreamer().with(Granularity.FIVE).count());
	}

	@Test
	public void testValidCountry() throws Exception {
		new GeoDeliveryStreamer().with(Granularity.FIVE).forEach(geoHashCountry -> {
			assertNotNull(geoHashCountry.getPack());
		});
	}

	@Test
	public void testValidGeoHash() throws Exception {
		new GeoDeliveryStreamer().with(Granularity.FIVE).forEach(geoHashCountry -> {
			assertNotNull(geoHashCountry.getPath());
			assertFalse(geoHashCountry.getPath().isEmpty());
		});
	}
}