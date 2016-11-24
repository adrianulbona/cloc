package io.github.adrianulbona.cloc.index;

import lombok.Data;

import java.util.stream.Stream;

/**
 * Created by adrianulbona on 30/08/16.
 */
@Data
public class Leaf<P> implements Node<P> {

	private final P pack;

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public <T> Stream<T> collect(PackageTransformer<P, T> transformer) {
		return Stream.of(transformer.transform(pack));
	}

}
