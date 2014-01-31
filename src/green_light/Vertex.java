package green_light;

public class Vertex {

    final int id;
    final String name;

    Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + 0;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Vertex)) {
            return false;
        }
        Vertex other = (Vertex) obj;
        if (id == 0) {
            if (other.id != 0) {
                return false;
            }
        } else if (id != other.id) {
            return false;
        }
        return true;
    }

    public String toString() {
        return name;
    }
}
