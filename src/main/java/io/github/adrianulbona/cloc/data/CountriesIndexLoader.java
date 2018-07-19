package io.github.adrianulbona.cloc.data;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toMap;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class CountriesIndexLoader {

    public Map<Integer, List<String>> load() {
        try (InputStream in = getClass().getResourceAsStream("countries.index");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return reader.lines()
                    .map(this::parseIdCountries)
                    .collect(toMap(idCountries -> idCountries.id, idCountries -> idCountries.countries));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load countries.index", e);
        }
    }

    private IdCountries parseIdCountries(String rawEntry) {
        final String[] idCountries = rawEntry.split("=");
        final Integer id = Integer.valueOf(idCountries[0]);
        final List<String> countries = Arrays.asList(idCountries[1].split(","));
        return new IdCountries(id, countries);
    }

    private Path indexPath() throws URISyntaxException {
        final URL resource = CountriesIndexLoader.class.getResource("countries.index");
        return get(resource.toURI());
    }

    @RequiredArgsConstructor
    private static class IdCountries {

        public final Integer id;
        public final List<String> countries;
    }
}
