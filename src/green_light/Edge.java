package green_light;

public class Edge {

    final String id;
    final Vertex source;
    final Vertex destination;
    final int weight;

    Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    String getId() {
        return id;
    }

    Vertex getDestination() {
        return destination;
    }

    Vertex getSource() {
        return source;
    }

    int getWeight() {
        return weight;
    }

    public String toString() {
        return source.toString() + " " + destination.toString();
    }
}
