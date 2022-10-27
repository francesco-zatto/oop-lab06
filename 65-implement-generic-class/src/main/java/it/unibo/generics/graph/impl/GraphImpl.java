package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> nodes;

    public GraphImpl() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(N node) {
        if (node != null && !this.nodes.containsKey(node)) {
            this.nodes.put(node, new HashSet<>());
        }    
    }

    @Override
    public void addEdge(N source, N target) {
        if (source != null && target != null) {
            this.nodes.get(source).add(target);
        }
        
    }

    @Override
    public Set<N> nodeSet() {
        return this.nodes.keySet();
    }

    @Override
    public Set<N> linkedNodes(N node) {
        return this.nodes.get(node);
    }

    @Override
    public List<N> getPath(N source, N target) {
        return new LinkedList<>();
    }
    
}
