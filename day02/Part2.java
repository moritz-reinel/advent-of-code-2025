import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.LongStream;

public class Part2
{
    private static boolean checkIfInvalid(long num) {
        var s = String.valueOf(num);

        windowSize:
        for (int ws = 1; ws < s.length(); ws++) {
            if (s.length() % ws != 0 || s.length() / ws == 1) continue;

            for (int i = 0; i < s.length() / ws; i++) {
                if (!s.regionMatches(0, s, i*ws, ws)) continue windowSize;
            }

            return true;
        }

        return false;
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
