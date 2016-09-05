package io.github.adrianulbona.cloc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoDeliveryStreamer {


	public Stream<GeoDelivery<String>> with(Granularity granularity) throws IOException {
		return Files.lines(granularity.path).map(this::parse);
	}

	private GeoDelivery<String> parse(String rawGeoUnit) {
		final int indexOfSeparator = rawGeoUnit.indexOf(",");
		final String geoHash = rawGeoUnit.substring(0, indexOfSeparator);
		final String country = rawGeoUnit.substring(indexOfSeparator + 1, rawGeoUnit.length());
		final List<GeoSymbol> path = geoHash.chars().mapToObj(GeoSymbol::decode).collect(toList());
		return new GeoDelivery<>(path, country);
	}

	public enum Granularity {
		FIVE(Paths.get("data/cloc5.csv")),
		SIX(Paths.get("data/cloc6.csv"));

		private final Path path;

		Granularity(Path path) {
			this.path = path;
		}
	}
}
