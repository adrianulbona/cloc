package io.github.adrianulbona.cloc;

import io.github.adrianulbona.cloc.index.*;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * Created by adrianulbona on 02/09/16.
 */
@AllArgsConstructor
public class GeoDriver<P> {

	private final NodeFactory<P> nodeFactory;
	private final LeafFactory<P> leafFactory;

	public Node<P> deliverMultiple(Stream<GeoDelivery<P>> deliveries) {
		final Node<P> root = this.nodeFactory.create();
		deliveries.forEach(delivery -> deliverTo(root, delivery));
		return root;
	}

	private void deliverTo(Node<P> currentNode, GeoDelivery<P> delivery) {
		final GeoSymbol currentSymbol = delivery.currentSymbol();
		if (delivery.atDestination()) {
			currentNode.createChildAndOverwriteIfNeeded(currentSymbol, () -> this.leafFactory.leaf(delivery.getPack()));
		} else {
			final Node<P> child = currentNode.getChildOrCreate(currentSymbol, this.nodeFactory);
			deliverTo(child, delivery.advance());
		}
	}

	public static <P> GeoDriver<P> withEmptyRoot() {
		return new GeoDriver<>(NodeImpl::new, Leaf::new);
	}
}
