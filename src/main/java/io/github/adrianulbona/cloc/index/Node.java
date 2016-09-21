package io.github.adrianulbona.cloc.index;

import io.github.adrianulbona.cloc.index.geo.Symbol;
import lombok.Data;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Stream.empty;

/**
 * Created by adrianulbona on 30/08/16.
 */
public interface Node<P> {

	default Node<P> getChildOrCreate(Symbol symbol, NodeFactory<P> nodeFactory) {
		throw new UnsupportedOperationException();
	}

	default Node<P> getChildOr(Symbol symbol, Node<P> orNode) {
		throw new UnsupportedOperationException();
	}

	default void createChildAndOverwriteIfNeeded(Symbol symbol, NodeFactory<P> nodeFactory) {
		throw new UnsupportedOperationException();
	}

	boolean hasChildren();

	default Stream<P> collect() {
		return collect(p -> p);
	}

	<T> Stream<T> collect(PackageTransformer<P, T> transformer);

	@Data
	class EmptyNode<P> implements Node<P>{

		@Override
		public boolean hasChildren() {
			return false;
		}

		@Override
		public <T> Stream<T> collect(PackageTransformer<P, T> transformer) {
			return empty();
		}
	}

	interface PackageTransformer<P, T> {

		T transform(P p);
	}
}
