import java.io.IOException;
import java.nio.file.*;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        var parts = Files.readString(Path.of("input.txt")).split("\n\n");

        var ranges = parts[0].lines().map(line -> {
            var bounds = line.split("-");
            return new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
        }).toList();

        long result = parts[1].lines()
            .mapToLong(idStr -> {
                long id = Long.parseLong(idStr);
                return ranges.stream().anyMatch(range -> range.includes(id)) ? 1 : 0;
            })
            .sum();

        System.out.println(result);
    }

    record Range(long from, long to) {
        boolean includes(long n) {
            return n >= from && n <= to;
        }
    }
}
