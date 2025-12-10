import java.io.IOException;
import java.nio.file.*;
import java.util.stream.IntStream;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        var points = Files.lines(Path.of("input.txt"))
            .map(line -> {
                var xy = line.split(",");
                return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            }).toList();

        var edges = IntStream.range(0, points.size())
            .mapToObj(i -> Area.from(points.get(i), points.get((i + 1) % points.size())))
            .toList();

        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                var area = Area.from(points.get(i), points.get(j));
                boolean overlaps = edges.stream().anyMatch(edge -> area.overlapsWith(edge));

                if (!overlaps) maxArea = Math.max(maxArea, area.area());
            }
        }

        System.out.println(maxArea);
    }

    record Point(int x, int y) {}

    record Area(int xMin, int xMax, int yMin, int yMax) {
        static Area from(Point a, Point b) {
            return new Area(
                Math.min(a.x, b.x),
                Math.max(a.x, b.x),
                Math.min(a.y, b.y),
                Math.max(a.y, b.y)
            );
        }

        long area() {
            int dx = this.xMax - this.xMin + 1;
            int dy = this.yMax - this.yMin + 1;
            return 1L * dx * dy;
        }

        boolean overlapsWith(Area other) {
            return (
                this.xMax > other.xMin && other.xMax > this.xMin &&
                this.yMax > other.yMin && other.yMax > this.yMin
            );
        }
    }
}
