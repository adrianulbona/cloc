package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.data.AnnotatedGeoHashStreamer;
import io.github.adrianulbona.cloc.index.Node;
import io.github.adrianulbona.cloc.index.geo.Delivery;
import io.github.adrianulbona.cloc.index.geo.Driver;
import io.github.adrianulbona.cloc.index.geo.Symbol;

import java.io.IOException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class GeoHashTreeLoader {

	public Node<Integer> load() throws IOException {
		final Stream<Delivery<Integer>> deliveryStream = new AnnotatedGeoHashStreamer().stream().map(ag ->
				new Delivery<>(
						ag.getGeoHash().chars().mapToObj(Symbol::decode).collect(toList()),
						ag.getCountriesIndex()));
		return Driver.<Integer>withEmptyRoot().deliverMultiple(deliveryStream);
	}
}
