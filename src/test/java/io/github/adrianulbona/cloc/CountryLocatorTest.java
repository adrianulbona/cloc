package io.github.adrianulbona.cloc;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

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
		final TreeSet<String> countries = new TreeSet<>();
		for (final Country country : countryLocator.locateCountry("g")) {
			countries.add(country.name);
		}
		final TreeSet<String> expected = new TreeSet<>(Arrays.asList(
				"Greenland",
				"Iceland",
				"Norway",
				"France",
				"United Kingdom",
				"Jersey",
				"Guernsey",
				"Faroe Islands",
				"Isle of Man",
				"Ireland"));
		assertEquals(10, countries.size());
		assertEquals(expected, countries);
	}

	@Test
	public void locateShortGeoHashJson() {
		final TreeSet<String> countries = new TreeSet<>();
		for (final Country country : countryLocator.locateCountry("g")) {
			countries.add(country.toString());
		}
		final TreeSet<String> expected = new TreeSet<>(Arrays.asList(
				"{\"name\":\"Greenland\",\"iso2\":\"GL\",\"iso3\":\"GRL\",\"isoId\":304}",
				"{\"name\":\"Iceland\",\"iso2\":\"IS\",\"iso3\":\"ISL\",\"isoId\":352}",
				"{\"name\":\"Norway\",\"iso2\":\"NO\",\"iso3\":\"NOR\",\"isoId\":578}",
				"{\"name\":\"France\",\"iso2\":\"FR\",\"iso3\":\"FRA\",\"isoId\":250}",
				"{\"name\":\"United Kingdom\",\"iso2\":\"GB\",\"iso3\":\"GBR\",\"isoId\":826}",
				"{\"name\":\"Jersey\",\"iso2\":\"JE\",\"iso3\":\"JEY\",\"isoId\":832}",
				"{\"name\":\"Guernsey\",\"iso2\":\"GG\",\"iso3\":\"GGY\",\"isoId\":831}",
				"{\"name\":\"Faroe Islands\",\"iso2\":\"FO\",\"iso3\":\"FRO\",\"isoId\":234}",
				"{\"name\":\"Isle of Man\",\"iso2\":\"IM\",\"iso3\":\"IMN\",\"isoId\":833}",
				"{\"name\":\"Ireland\",\"iso2\":\"IE\",\"iso3\":\"IRL\",\"isoId\":372}"));
		assertEquals(10, countries.size());
		assertEquals(expected, countries);
	}

	@Test
	public void locateLongGeoHash() {
		final List<String> countries = countryLocator.locate("u10hb45454");
		assertEquals(1, countries.size());
		assertEquals("United Kingdom", countries.get(0));
	}

	@Test
	public void locateLongGeoHashJson() {
		final List<Country> countries = countryLocator.locateCountry("u10hb45454");
		assertEquals(1, countries.size());
		assertEquals("{\"name\":\"United Kingdom\",\"iso2\":\"GB\",\"iso3\":\"GBR\",\"isoId\":826}", countries.get(0).toString());
	}
}