package io.github.adrianulbona.cloc.index;

import io.github.adrianulbona.cloc.index.Node;

/**
 * Created by adrianulbona on 02/09/16.
 */
public interface NodeFactory<P> {

	Node<P> create();
}
