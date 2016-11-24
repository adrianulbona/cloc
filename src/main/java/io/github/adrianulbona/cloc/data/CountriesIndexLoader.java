package io.github.adrianulbona.cloc.data;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Created by adrianulbona on 30/08/16.
 */
public class CountriesIndexLoader {

	public Map<Integer, List<String>> load() throws IOException {
		try (final Stream<String> lines = Files.lines(indexPath())) {
			return lines.collect(toList())
					.stream()
					.map(this::parseIdCountries)
					.collect(toMap(idCountries -> idCountries.id, idCountries -> idCountries.countries));
		}
	}

	private IdCountries parseIdCountries(String rawEntry) {
		final String[] idCountries = rawEntry.split("=");
		final Integer id = Integer.valueOf(idCountries[0]);
		final List<String> countries = Arrays.asList(idCountries[1].split(","));
		return new IdCountries(id, countries);
	}

	private Path indexPath() {
		try {
			final URL resource = CountriesIndexLoader.class.getResource("countries.index");
			return get(resource.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@RequiredArgsConstructor
	private static class IdCountries {

		public final Integer id;
		public final List<String> countries;
	}
}
