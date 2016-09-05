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
	public Stream<P> packages() {
		return Stream.of(pack);
	}
}
