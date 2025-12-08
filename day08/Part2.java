import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part2
{
    // Union find
    private final static Map<Coord, Coord> parent = new HashMap<>();

    private static Coord find(Coord x) {
        parent.putIfAbsent(x, x);

        var p = parent.get(x);
        if (x.equals(p)) return x;

        parent.put(x, find(p));
        return parent.get(x);
    }

    private static void union(Coord a, Coord b) {
        var rootA = find(a);
        var rootB = find(b);
        if (!rootA.equals(rootB)) parent.put(rootA, rootB);
    }

    public static void main(String[] args) throws IOException
    {
        var points = Files.lines(Path.of("input.txt"))
            .map(line -> {
                var coords = line.split(",");
                assert coords.length == 3;
                return new Coord(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
            }).toList();

        var connections = new ArrayList<Connection>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                var a = points.get(i);
                var b = points.get(j);
                double dist = a.dist(b);
                connections.add(new Connection(a, b, dist));
            }
        }
        connections.sort(Comparator.comparingDouble(Connection::cost));

        int connCount = 0;
        for (int i = 0; i < connections.size(); i++) {
            var conn = connections.get(i);

            if (!find(conn.from).equals(find(conn.to))) {
                connCount++;

                if (connCount == (points.size() - 1)) {
                    System.out.println(1L * conn.from.x * conn.to.x);
                    break;
                }
            }

            union(conn.from, conn.to);
        }
    }

    record Coord(int x, int y, int z) {
        double dist(Coord other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            double dz = this.z - other.z;
            return Math.hypot(Math.hypot(dx, dy), dz);
        }
    }

    record Connection(Coord from, Coord to, double cost) {}
}
