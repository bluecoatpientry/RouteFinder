package green_light;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DAlgorithm {

    final ArrayList<Vertex> nodes;
    final ArrayList<Edge> edges;
    HashSet<Vertex> settledNodes;
    HashSet<Vertex> unSettledNodes;
    HashMap<Vertex, Vertex> predecessors;
    HashMap<Vertex, Integer> distance;

    DAlgorithm(Graph graph) {
        nodes = graph.getVertexes();
        edges = graph.getEdges();
    }

    void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (target == null) {
                continue;
            }
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        //print("Should not happen");
        return 0;
    }

    List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    Vertex getMinimum(HashSet<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    int getShortestDistance(Vertex destination) {
        System.out.println(destination);
        if (distance.get(destination) == null) {
            return 999999;
        }
        int d = distance.get(destination);
        if (d == 0) {
            d = 99999;
        }
        return d;
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    ArrayList<Vertex> getPath(Vertex target) {
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;   // Put it into the correct order
    }
}
