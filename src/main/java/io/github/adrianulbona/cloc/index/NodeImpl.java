package io.github.adrianulbona.cloc.index;

import io.github.adrianulbona.cloc.index.geo.Symbol;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by adrianulbona on 30/08/16.
 */

@Data
public class NodeImpl<P> implements Node<P> {

	private final Map<Symbol, Node<P>> children = new HashMap<>();

	@Override
	public Node<P> getChildOrCreate(Symbol symbol, NodeFactory<P> nodeFactory) {
		return this.children.computeIfAbsent(symbol, key -> nodeFactory.create());
	}

	@Override
	public Node<P> getChildOr(Symbol symbol, Node<P> defaultNode) {
		return this.children.getOrDefault(symbol, defaultNode);
	}

	@Override
	public void createChildAndOverwriteIfNeeded(Symbol symbol, NodeFactory<P> nodeFactory) {
		this.children.put(symbol, nodeFactory.create());
	}

	@Override
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	@Override
	public Stream<P> packages() {
		return this.children.values().stream().flatMap(Node::packages).distinct();
	}
}
