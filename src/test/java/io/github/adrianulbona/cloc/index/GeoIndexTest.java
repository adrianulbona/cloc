package io.github.adrianulbona.cloc.index;

import io.github.adrianulbona.cloc.GeoDelivery;
import io.github.adrianulbona.cloc.GeoDeliveryStreamer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.github.adrianulbona.cloc.GeoDeliveryStreamer.Granularity;
import static io.github.adrianulbona.cloc.GeoSymbol.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.empty;
import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoIndexTest {
/*
	@Test
	public void testEmptyGeoUnitStream() throws Exception {
		final Node<String> root = new GeoIndex().deliver(empty());
		assertFalse(root.packages().findAny().isPresent());
	}

	@Test
	public void testWithOneGeoUnit() throws Exception {
		final GeoDelivery<String> geoDelivery = new GeoDelivery<>(Arrays.asList(GS_0, GS_1, GS_3), "Romania");
		final Node<String> root = new GeoIndex().deliver(Stream.of(geoDelivery));

		final List<String> countries = root.packages().collect(toList());
		assertEquals(1, countries.size());
		assertEquals("Romania", countries.get(0));
	}

	@Test
	public void testGeoHash5() throws Exception {
		final Stream<GeoDelivery<String>> geoDeliveries = new GeoDeliveryStreamer().with(Granularity.FIVE);
		final Node root = new GeoIndex().deliver(geoDeliveries);
		assertNotNull(root);
	}

	@Test
	public void testGeoHash6() throws Exception {
		final Stream<GeoDelivery<String>> geoDeliveries = new GeoDeliveryStreamer().with(Granularity.SIX);
		final Node root = new GeoIndex().deliver(geoDeliveries);
		assertNotNull(root);
	}
	*/
}