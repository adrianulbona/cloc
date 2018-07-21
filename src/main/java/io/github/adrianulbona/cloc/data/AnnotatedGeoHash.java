package io.github.adrianulbona.cloc.data;

import lombok.Data;

/**
 * Created by adrianulbona on 21/09/16.
 */
@Data
public class AnnotatedGeoHash {

    private final String geoHash;
    private final int countriesIndex;
}
