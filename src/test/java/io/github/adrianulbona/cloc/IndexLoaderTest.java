package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.index.Node;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrianulbona on 21/09/16.
 */
public class IndexLoaderTest {

	@Test
	public void build() throws Exception {
		final Node<String[]> index = new IndexLoader().load();
		assertNotNull(index);
		assertTrue(index.hasChildren());
	}
}