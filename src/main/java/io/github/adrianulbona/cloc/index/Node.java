package io.github.adrianulbona.cloc.index;

import io.github.adrianulbona.cloc.GeoSymbol;
import lombok.Data;

import java.util.stream.Stream;

import static java.util.stream.Stream.empty;

/**
 * Created by adrianulbona on 30/08/16.
 */
public interface Node<P> {

	default Node<P> getChildOrCreate(GeoSymbol symbol, NodeFactory<P> nodeFactory) {
		throw new UnsupportedOperationException();
	}

	default Node<P> getChildOr(GeoSymbol symbol, Node<P> orNode) {
		throw new UnsupportedOperationException();
	}

	default void createChildAndOverwriteIfNeeded(GeoSymbol symbol, NodeFactory<P> nodeFactory) {
		throw new UnsupportedOperationException();
	}

	boolean hasChildren();

	Stream<P> packages();

	@Data
	class EmptyNode<P> implements Node<P>{

		@Override
		public boolean hasChildren() {
			return false;
		}

		@Override
		public Stream<P> packages() {
			return empty();
		}
	}
}
