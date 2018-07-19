package io.github.adrianulbona.cloc.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static java.lang.Integer.valueOf;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class AnnotatedGeoHashStreamer {

    public Stream<AnnotatedGeoHash> stream() {
        try (InputStream in = getClass().getResourceAsStream("geohashes.csv.gz");
             GZIPInputStream gzip = new GZIPInputStream(in);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzip));
             Stream<String> lines = reader.lines()) {
            return lines.collect(Collectors.toList())
                    .stream()
                    .map(this::parseAnnotatedGeoHash);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load geohashes.csv.gz", e);
        }
    }

    private AnnotatedGeoHash parseAnnotatedGeoHash(String line) {
        final String[] geoHashCountriesIndex = line.split(",");
        return new AnnotatedGeoHash(geoHashCountriesIndex[0], valueOf(geoHashCountriesIndex[1]));
    }
}
