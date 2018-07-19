package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.data.AnnotatedGeoHash;
import io.github.adrianulbona.cloc.data.AnnotatedGeoHashStreamer;
import io.github.adrianulbona.cloc.index.Node;
import io.github.adrianulbona.cloc.index.geo.Delivery;
import io.github.adrianulbona.cloc.index.geo.Driver;
import io.github.adrianulbona.cloc.index.geo.Symbol;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class GeoHashTreeLoader {

    public Node<Integer> load() {
        final AnnotatedGeoHashStreamer annotatedGeoHashes = new AnnotatedGeoHashStreamer();
        final Stream<Delivery<Integer>> deliveryStream = annotatedGeoHashes.stream()
                .map(this::annotatedGeoHash2Delivery);
        return Driver.<Integer>withEmptyRoot().deliverMultiple(deliveryStream);
    }

    private Delivery<Integer> annotatedGeoHash2Delivery(AnnotatedGeoHash annotatedGeoHash) {
        final List<Symbol> symbols = annotatedGeoHash.getGeoHash()
                .chars()
                .mapToObj(Symbol::decode)
                .collect(toList());
        return new Delivery<>(symbols, annotatedGeoHash.getCountriesIndex());
    }
}
