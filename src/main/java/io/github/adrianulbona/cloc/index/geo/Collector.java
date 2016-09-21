package io.github.adrianulbona.cloc.index.geo;

import io.github.adrianulbona.cloc.index.Node;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by adrianulbona on 04/09/16.
 */
public class Collector {

	public <P> List<P> collect(Node<P> node, List<Symbol> path) {
		if (path.isEmpty() || !node.hasChildren()) {
			return node.packages().collect(toList());
		} else {
			final Symbol head = path.get(0);
			final Node<P> child = node.getChildOr(head, new Node.EmptyNode<>());
			final List<Symbol> tail = path.subList(1, path.size());
			return collect(child, tail);
		}
	}
}
