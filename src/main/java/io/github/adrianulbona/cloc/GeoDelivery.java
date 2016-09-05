package io.github.adrianulbona.cloc;

import lombok.Data;

import java.util.List;

/**
 * Created by adrianulbona on 30/08/16.
 */
@Data
public class GeoDelivery<P> {

	private final List<GeoSymbol> path;
	private final P pack;

	public boolean atDestination() {
		return this.path.size() == 1;
	}

	public GeoDelivery<P> advance() {
		return new GeoDelivery<P>(path.subList(1, path.size()), pack);
	}

	public GeoSymbol currentSymbol() {
		return this.path.get(0);
	}
}
