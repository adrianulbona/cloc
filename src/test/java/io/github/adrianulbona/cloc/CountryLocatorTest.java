package io.github.adrianulbona.cloc;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class CountryLocatorTest {

	private static CountryLocator countryLocator;

	@BeforeClass
	public static void setUp() {
		countryLocator = CountryLocator.create();
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateNull() {
		countryLocator.locate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateEmpty() {
		countryLocator.locate("");
	}

	@Test
	public void locateShortGeoHash() {
		final List<String> countries = countryLocator.locate("g");
		assertEquals(10, countries.size());
	}

	@Test
	public void locateLongGeoHash() {
		final List<String> countries = countryLocator.locate("u10hb45454");
		assertEquals(1, countries.size());
		assertEquals("United Kingdom", countries.get(0));
	}
}