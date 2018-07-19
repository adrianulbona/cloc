package io.github.adrianulbona.cloc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.assertEquals;

public class ResourceLoadingTest {

    @Test
    public void rawLoad() throws IOException {
        try (InputStream in = getClass().getResourceAsStream("data/countries.index");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            assertEquals(751, reader.lines().count());
        }
    }

    @Test
    public void gzLoad() throws IOException {
        try (InputStream in = getClass().getResourceAsStream("data/geohashes.csv.gz");
             GZIPInputStream gzip = new GZIPInputStream(in);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzip));
             Stream<String> lines = reader.lines()) {
            assertEquals(1195623, lines.count());
        }
    }
}
