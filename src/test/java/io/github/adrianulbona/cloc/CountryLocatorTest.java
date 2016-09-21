package io.github.adrianulbona.cloc;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class CountryLocatorTest {

	private CountryLocator countryLocator;

	@Before
	public void setUp() throws Exception {
		this.countryLocator = CountryLocator.fromFreshIndex();
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateNull() throws Exception {
		this.countryLocator.locate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void locateEmpty() throws Exception {
		this.countryLocator.locate("");
	}

	@Test
	public void locateShortGeoHash() throws Exception {
		final List<String> countries = this.countryLocator.locate("g");
		assertEquals(10, countries.size());
	}

	@Test
	public void locateLongGeoHash() throws Exception {
		final List<String> countries = this.countryLocator.locate("u10hb45454");
		assertEquals(1, countries.size());
		assertEquals("United Kingdom", countries.get(0));
	}
}