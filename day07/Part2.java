import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part2
{
    record Coord(int y, int x) {}

    private static List<ArrayList<Character>> field;

    public static void main(String[] args) throws IOException
    {
        field = Files.lines(Path.of("input.txt"))
			.map(line -> new ArrayList<>(line.chars().mapToObj(c -> (char) c).toList()))
			.toList();

        int sourceX = field.getFirst().indexOf('S');

        long result = calc(new Coord(0, sourceX));
        System.out.println(result);
    }

    private static Map<Coord, Long> cache = new HashMap<>();

    private static long calc(Coord coord) {
        var cacheItem = cache.get(coord);
        if (cacheItem != null) return cacheItem;

        if (coord.y + 1 == field.size())
            return 1;

        if (field.get(coord.y + 1).get(coord.x) == '^') {
            long tmp = calc(new Coord(coord.y + 1, coord.x - 1)) + calc(new Coord(coord.y + 1, coord.x + 1));
            cache.put(coord, tmp);
            return tmp;
        }

        long tmp = calc(new Coord(coord.y + 1, coord.x));
        cache.put(coord, tmp);
        return tmp;
    }
}
