package io.github.adrianulbona.cloc.index.geo;

import io.github.adrianulbona.cloc.data.AnnotatedGeoHash;
import io.github.adrianulbona.cloc.data.AnnotatedGeoHashStreamer;
import io.github.adrianulbona.cloc.index.Node;
import io.github.adrianulbona.cloc.index.geo.Collector;
import io.github.adrianulbona.cloc.index.geo.Delivery;
import io.github.adrianulbona.cloc.index.geo.Driver;
import io.github.adrianulbona.cloc.index.geo.Symbol;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.github.adrianulbona.cloc.index.geo.Symbol.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 04/09/16.
 */
public class CollectorTest {

	@Test
	public void collect() throws Exception {
		final Driver<String[]> driver = Driver.withEmptyRoot();
		final Stream<AnnotatedGeoHash> annotatedGeoHashStream = new AnnotatedGeoHashStreamer().stream();
		final Stream<Delivery<String[]>> deliveryStream = annotatedGeoHashStream.map(ag ->
				new Delivery<>(
						ag.getGeoHash().chars().mapToObj(Symbol::decode).collect(toList()),
						ag.getAnnotations()));
		final Node<String[]> root = driver.deliverMultiple(deliveryStream);

		final long t = System.currentTimeMillis();
		final List<String[]> countries1 = new Collector().collect(root, asList(GS_u, GS_3, GS_3));
		System.out.println(System.currentTimeMillis() - t);
		System.out.println(countries1.stream().flatMap(Arrays::stream).distinct().collect(toList()));
		final long t1 = System.currentTimeMillis();
		final List<String[]> countries2 = new Collector().collect(root, asList(GS_9, GS_q, GS_x));
		System.out.println(System.currentTimeMillis() - t1);
		System.out.println(countries2.stream().flatMap(Arrays::stream).distinct().collect(toList()));
	}

}