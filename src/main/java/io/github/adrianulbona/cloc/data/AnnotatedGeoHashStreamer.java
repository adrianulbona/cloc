package io.github.adrianulbona.cloc.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class AnnotatedGeoHashStreamer {

	public Stream<AnnotatedGeoHash> stream() throws IOException {
		try (final InputStream inputStream = AnnotatedGeoHashStreamer.class.getResourceAsStream("geohashes.csv.gz");
			 final GZIPInputStream gzip = new GZIPInputStream(inputStream);
			 final BufferedReader reader = new BufferedReader(new InputStreamReader(gzip));
			 final Stream<String> lines = reader.lines()) {
			return lines.collect(Collectors.toList())
					.stream()
					.map(this::parseAnnotatedGeoHash);
		}
	}

	private AnnotatedGeoHash parseAnnotatedGeoHash(String line) {
		final String[] geoHashCountriesIndex = line.split(",");
		return new AnnotatedGeoHash(geoHashCountriesIndex[0],
				Integer.valueOf(geoHashCountriesIndex[1]));
	}
}
