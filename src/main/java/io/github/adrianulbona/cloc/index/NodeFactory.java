package io.github.adrianulbona.cloc.index;

/**
 * Created by adrianulbona on 02/09/16.
 */
public interface NodeFactory<P> {

    Node<P> create();
}
