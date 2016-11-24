package io.github.adrianulbona.cloc.data;

import lombok.Data;

import java.util.List;

/**
 * Created by adrianulbona on 21/09/16.
 */
@Data
public class AnnotatedGeoHash {

	private final String geoHash;
	private final int countriesIndex;
}
