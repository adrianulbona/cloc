package io.github.adrianulbona.cloc.index.geo;

import io.github.adrianulbona.cloc.index.*;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * Created by adrianulbona on 02/09/16.
 */
@AllArgsConstructor
public class Driver<P> {

	private final NodeFactory<P> nodeFactory;
	private final LeafFactory<P> leafFactory;

	public Node<P> deliverMultiple(Stream<Delivery<P>> deliveries) {
		final Node<P> root = this.nodeFactory.create();
		deliveries.forEach(delivery -> deliverTo(root, delivery));
		return root;
	}

	private void deliverTo(Node<P> currentNode, Delivery<P> delivery) {
		final Symbol currentSymbol = delivery.currentSymbol();
		if (delivery.atDestination()) {
			currentNode.createChildAndOverwriteIfNeeded(currentSymbol, () -> this.leafFactory.leaf(delivery.getPack()));
		} else {
			final Node<P> child = currentNode.getChildOrCreate(currentSymbol, this.nodeFactory);
			deliverTo(child, delivery.advance());
		}
	}

	public static <P> Driver<P> withEmptyRoot() {
		return new Driver<>(NodeImpl::new, Leaf::new);
	}
}
