import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        var parts = Files.readString(Path.of("input.txt")).split("\n\n");

        var ranges = parts[0].lines().map(line -> {
            var bounds = line.split("-");
            return new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
        }).toList();

        ranges = new ArrayList<>(ranges);
        Collections.sort(ranges, (r1, r2) -> Long.compare(r1.from, r2.from));

        long result = 0;
        long head = 0;
        for (var range : ranges) {
            long start = range.from > head ? range.from : head + 1;

            if (range.to >= start) {
                result += (range.to - start + 1);
                head = range.to;
            }
        }

        System.out.println(result);
    }

    record Range(long from, long to) {
        boolean includes(long n) {
            return n >= from && n <= to;
        }
    }
}
