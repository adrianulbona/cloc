package io.github.adrianulbona.cloc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoDeliveryStreamer {


	public Stream<GeoDelivery<String>> with(Granularity granularity) throws IOException {
		final GZIPInputStream gzip = new GZIPInputStream(Files.newInputStream(granularity.path));
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(gzip))) {
			return reader.lines().collect(toList()).stream().map(this::parse);
		}
	}

	private GeoDelivery<String> parse(String rawGeoUnit) {
		final int indexOfSeparator = rawGeoUnit.indexOf(",");
		final String geoHash = rawGeoUnit.substring(0, indexOfSeparator);
		final String country = rawGeoUnit.substring(indexOfSeparator + 1, rawGeoUnit.length());
		final List<GeoSymbol> path = geoHash.chars().mapToObj(GeoSymbol::decode).collect(toList());
		return new GeoDelivery<>(path, country);
	}

	public enum Granularity {
		FIVE("cloc5.csv.gz");

		public final Path path;

		Granularity(String path) {
			try {
				this.path = Paths.get(GeoDeliveryStreamer.class.getResource(path).toURI().getPath());
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
