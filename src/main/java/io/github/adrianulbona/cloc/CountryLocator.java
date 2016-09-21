package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.index.Node;
import io.github.adrianulbona.cloc.index.geo.Collector;
import io.github.adrianulbona.cloc.index.geo.Symbol;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 21/09/16.
 */

@AllArgsConstructor
public class CountryLocator {

	private final Node<String[]> index;

	public List<String> locate(String geoHash) {
		if (geoHash == null || geoHash.isEmpty()) {
			throw new IllegalArgumentException();
		}
		final List<String[]> packages = new Collector().collect(this.index, Symbol.decodeMultiple(geoHash));
		return packages.stream().flatMap(Arrays::stream).distinct().collect(toList());
	}

	public static CountryLocator fromFreshIndex() throws IOException {
		return new CountryLocator(new IndexLoader().load());
	}
}
