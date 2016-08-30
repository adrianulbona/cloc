package io.github.adrianulbona.cloc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoHashCountryStreamerTest {

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(new GeoHashCountryStreamer().stream());
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(9505218, new GeoHashCountryStreamer().stream().count());
    }

    @Test
    public void testValidCountry() throws Exception {
        new GeoHashCountryStreamer().stream().forEach(geoHashCountry -> {
            assertNotNull(geoHashCountry.getCountry());
            assertFalse(geoHashCountry.getCountry().isEmpty());
        });
    }

    @Test
    public void testValidGeoHash() throws Exception {
        new GeoHashCountryStreamer().stream().forEach(geoHashCountry -> {
            assertNotNull(geoHashCountry.getGeoHash());
            assertFalse(geoHashCountry.getGeoHash().isEmpty());
        });
    }
}