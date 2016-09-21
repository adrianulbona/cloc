package io.github.adrianulbona.cloc.data;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class AnnotatedGeoHashStreamer {


	public Stream<AnnotatedGeoHash> stream() throws IOException, URISyntaxException {
		final Path path = Paths.get(AnnotatedGeoHashStreamer.class.getResource("cloc5.json.gz").toURI().getPath());
		final GZIPInputStream gzip = new GZIPInputStream(Files.newInputStream(path));
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(gzip))) {
			final Gson gson = new Gson();
			return reader.lines().collect(toList()).stream().map(line -> gson.fromJson(line, AnnotatedGeoHash.class));
		}
	}
}
