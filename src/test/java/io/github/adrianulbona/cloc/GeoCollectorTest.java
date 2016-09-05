package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.GeoDeliveryStreamer.Granularity;
import io.github.adrianulbona.cloc.index.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.github.adrianulbona.cloc.GeoSymbol.*;
import static java.util.Arrays.asList;

/**
 * Created by adrianulbona on 04/09/16.
 */
public class GeoCollectorTest {

	@Test
	public void collect() throws Exception {
		final GeoDriver<String> driver = GeoDriver.withEmptyRoot();
		final Stream<GeoDelivery<String>> deliveryStream = new GeoDeliveryStreamer().with(Granularity.FIVE);
		final Node<String> root = driver.deliverMultiple(deliveryStream);

		final long t = System.currentTimeMillis();
		final List<String> countries1 = new GeoCollector().collect(root, asList(GS_u, GS_3, GS_3));
		System.out.println(System.currentTimeMillis()- t);
		System.out.println(countries1);
		final long t1 = System.currentTimeMillis();
		final List<String> countries2 = new GeoCollector().collect(root, asList(GS_9, GS_q, GS_x));
		System.out.println(System.currentTimeMillis()- t1);

		System.out.println(countries2);

	}

}