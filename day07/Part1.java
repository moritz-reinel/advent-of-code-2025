import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part1
{
    record Coord(int y, int x) {}

    public static void main(String[] args) throws IOException
    {
        var field = Files.lines(Path.of("input.txt"))
			.map(line -> new ArrayList<>(line.chars().mapToObj(c -> (char) c).toList()))
			.toList();

        int sourceX = field.getFirst().indexOf('S');

        int result = 0;
        var visited = new HashSet<>();
        var todo = new ArrayDeque<Coord>();
        todo.add(new Coord(0, sourceX));

        while (!todo.isEmpty()) {
            var e = todo.pop();
            if (!visited.add(e)) continue;
            if (e.y + 1 == field.size()) continue;

            if (field.get(e.y + 1).get(e.x) == '^') {
                todo.add(new Coord(e.y + 1, e.x - 1));
                todo.add(new Coord(e.y + 1, e.x + 1));
                result++;
            } else {
                todo.add(new Coord(e.y + 1, e.x));
            }
        }

        System.out.println(result);
    }
}
