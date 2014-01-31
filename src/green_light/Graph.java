package green_light;

import java.util.ArrayList;

public class Graph {

    final ArrayList<Vertex> vertexes;
    final ArrayList<Edge> edges;

    Graph(ArrayList<Vertex> vertexes, ArrayList<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    ArrayList<Vertex> getVertexes() {
        return vertexes;
    }

    ArrayList<Edge> getEdges() {
        return edges;
    }
}
