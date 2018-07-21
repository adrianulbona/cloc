package io.github.adrianulbona.cloc.index;

/**
 * Created by adrianulbona on 02/09/16.
 */
public interface LeafFactory<P> {

    Node<P> leaf(P pack);
}
