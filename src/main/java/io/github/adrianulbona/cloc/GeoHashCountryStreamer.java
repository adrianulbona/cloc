package io.github.adrianulbona.cloc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class GeoHashCountryStreamer {

	public static final Path CLOC6_PATH = Paths.get("data/cloc6.csv");

	public Stream<GeoHashCountry> stream() throws IOException {
		return Files.lines(CLOC6_PATH).map(this::parse);
	}

	private GeoHashCountry parse(String rawGeoHashCountry) {
		final int indexOfSeparator = rawGeoHashCountry.indexOf(",");
		final String geoHash = rawGeoHashCountry.substring(0, indexOfSeparator);
		final String country = rawGeoHashCountry.substring(indexOfSeparator + 1, rawGeoHashCountry.length());
		return new GeoHashCountry(geoHash, country);
	}
}
