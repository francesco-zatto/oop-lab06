package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private static enum Color {
        WHITE, BLACK
    }

    private final Map<N, Set<N>> nodes;

    public GraphImpl() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(final N node) {
        if (node != null && !this.nodes.containsKey(node)) {
            this.nodes.put(node, new HashSet<>());
        }    
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (source != null && target != null) {
            this.nodes.get(source).add(target);
        }
        
    }

    @Override
    public Set<N> nodeSet() {
        return this.nodes.keySet();
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return this.nodes.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        final Map<N, N> predecessors = this.bfs(source);
        if (predecessors.isEmpty()) {
            return null;
        }
        return computePath(predecessors, source, target);
    }

    private List<N> computePath(Map<N, N> predecessors, final N source, final N target) {
        List<N> path = new LinkedList<>();
        N tmp = target;
        while (predecessors.get(tmp) != tmp) {
            path.add(0, tmp);
            tmp = predecessors.get(tmp);
        }
        if (!path.isEmpty()) {
            path.add(0, source);

        }
        return path;
    }

    private Map<N, N> bfs(N source) {
        final Map<N, Color> colors = new HashMap<>();
        final Map<N, N> predecessors = new HashMap<>();

        for (N node : this.nodes.keySet()) {
            colors.put(node, Color.WHITE);
            predecessors.put(node, null);
        }

        colors.replace(source, Color.WHITE);
        predecessors.replace(source, source);
        Queue<N> nodeQueue = new LinkedList<>();
        nodeQueue.add(source);

        while (!nodeQueue.isEmpty()) {
            N headNode = nodeQueue.poll();
            for (N adj : this.nodes.get(headNode)) {
                if (colors.get(adj) == Color.WHITE) {
                    colors.replace(source, Color.BLACK);
                    predecessors.replace(adj, headNode);
                    nodeQueue.add(adj);
                }
            }
        }
        return predecessors;
    } 
    
}
