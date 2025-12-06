import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        var lines = Files.readAllLines(Path.of("input.txt"));
        var operands = Arrays.asList(lines.getLast().trim().split("\\s+"));
        var numsPerLine = lines.subList(0, lines.size() - 1).stream()
            .map(line -> Arrays.stream(line.trim().split("\\s+")).map(num -> Long.parseLong(num)).toList())
            .toList();

        long result = 0;

        for (int i = 0; i < operands.size(); i++) {
            char op = operands.get(i).charAt(0);
            long tmp = op == '*' ? 1 : 0;

            for (int l = 0; l < numsPerLine.size(); l++) {
                if (op == '*') {
                    tmp *= numsPerLine.get(l).get(i);
                } else {
                    tmp += numsPerLine.get(l).get(i);
                }
            }

            result += tmp;
        }

        System.out.println(result);
    }
}
