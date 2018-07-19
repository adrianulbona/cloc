package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.index.Node;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class GeoHashTreeLoaderTest {

	@Test
	public void build() {
		final Node<Integer> index = new GeoHashTreeLoader().load();
		assertNotNull(index);
		assertTrue(index.hasChildren());
	}
}