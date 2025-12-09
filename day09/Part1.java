import java.io.IOException;
import java.nio.file.*;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        var points = Files.lines(Path.of("input.txt"))
            .map(line -> {
                var xy = line.split(",");
                return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            }).toList();

        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (i == j) continue;

                var a = points.get(i);
                var b = points.get(j);
                int dx = Math.abs(a.x - b.x) + 1;
                int dy = Math.abs(a.y - b.y) + 1;

                long area = 1L * dx * dy;
                maxArea = Math.max(maxArea, area);
            }
        }

        System.out.println(maxArea);
    }

    record Point(int x, int y) {}
}
