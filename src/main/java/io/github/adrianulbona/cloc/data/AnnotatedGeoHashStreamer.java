package io.github.adrianulbona.cloc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class AnnotatedGeoHashStreamer {


	public Stream<AnnotatedGeoHash> stream() throws IOException {
		try (final InputStream inputStream = AnnotatedGeoHashStreamer.class.getResourceAsStream("cloc5.json.gz");
			 final GZIPInputStream gzip = new GZIPInputStream(inputStream);
			 final BufferedReader reader = new BufferedReader(new InputStreamReader(gzip))) {
			final Gson gson = new Gson();
			return reader.lines().collect(toList()).stream().map(line -> gson.fromJson(line, AnnotatedGeoHash.class));
		}
	}
}
