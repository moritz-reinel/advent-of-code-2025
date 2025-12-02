import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.LongStream;

public class Part1
{
    private static boolean checkIfInvalid(long num) {
        var s = String.valueOf(num);
        if (s.length() % 2 != 0) return false;

        return s.regionMatches(0, s, s.length() / 2, s.length() / 2);
    }

    public static void main(String[] args) throws IOException
    {
        long result = Arrays.stream(Files.readString(Path.of("input.txt")).strip().split(","))
            .parallel()
            .mapToLong(entry -> {
                var nums = entry.split("-");
                long start = Long.parseLong(nums[0]);
                long end = Long.parseLong(nums[1]);
                return LongStream.rangeClosed(start, end)
                    .map(n -> (checkIfInvalid(n) ? n : 0))
                    .sum();
            })
            .sum();

        System.out.println(result);
    }
}
