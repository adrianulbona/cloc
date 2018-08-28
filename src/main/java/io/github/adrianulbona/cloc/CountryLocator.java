package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.data.CountriesIndexLoader;
import io.github.adrianulbona.cloc.index.Node;
import io.github.adrianulbona.cloc.index.geo.Collector;
import io.github.adrianulbona.cloc.index.geo.Symbol;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 21/09/16.
 */

@AllArgsConstructor
public class CountryLocator implements Serializable {

    private final Node<Integer> geoHashTree;
    private final Map<Integer, List<String>> countriesIndex;

    public List<String> locate(String geoHash) {
        return locateCountryName(geoHash);
    }

    public List<String> locateCountryName(String geoHash) {
        if (geoHash == null || geoHash.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Integer> packages = new Collector().collect(this.geoHashTree, Symbol.decodeMultiple(geoHash));
        return packages.stream()
                .map(this.countriesIndex::get)
                .flatMap(List::stream)
                .distinct()
                .map(CountryLocator::extractName)
                .collect(toList());
    }

    public List<Country> locateCountry(String geoHash) {
        if (geoHash == null || geoHash.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<Integer> packages = new Collector().collect(this.geoHashTree, Symbol.decodeMultiple(geoHash));
        return packages.stream()
                .map(this.countriesIndex::get)
                .flatMap(List::stream)
                .distinct()
                .map(CountryLocator::extractCountry)
                .collect(toList());
    }

    private static String extractName(final String str) {
        return str.split(";")[0];
    }

    private static Country extractCountry(final String str) {
        final String[] data = str.split(";");
        return new Country(data[0], data[1], data[2], data[3]);
    }

    public static CountryLocator create() {
        final Node<Integer> load = new GeoHashTreeLoader().load();
        final Map<Integer, List<String>> countriesIndex = new CountriesIndexLoader().load();
        return new CountryLocator(load, countriesIndex);
    }
}
