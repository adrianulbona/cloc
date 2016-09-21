package io.github.adrianulbona.cloc.index.geo;

import lombok.Data;

import java.util.List;

/**
 * Created by adrianulbona on 30/08/16.
 */
@Data
public class Delivery<P> {

	private final List<Symbol> path;
	private final P pack;

	public boolean atDestination() {
		return this.path.size() == 1;
	}

	public Delivery<P> advance() {
		return new Delivery<>(path.subList(1, path.size()), pack);
	}

	public Symbol currentSymbol() {
		return this.path.get(0);
	}
}
