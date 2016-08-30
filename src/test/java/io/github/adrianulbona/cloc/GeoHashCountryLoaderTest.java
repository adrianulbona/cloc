package io.github.adrianulbona.cloc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoHashCountryLoaderTest {

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(new GeoHashCountryLoader().load());
    }

    @Test
    public void testGeoHashCountryCount() throws Exception {
        assertEquals(9505218, new GeoHashCountryLoader().load().size());
    }
}