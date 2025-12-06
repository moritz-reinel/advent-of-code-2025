import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        var lines = Files.lines(Path.of("input.txt")).map(line -> {
            var chars = line.chars().mapToObj(c -> ((char) c)).toList();
            return new ArrayList<>(chars);
        }).toList();

        long result = 0;

        char currentOp = ' ';
        List<Integer> currentOpNums = new ArrayList<>();

        while (true) {
            boolean isDelimColumn = lines.stream().filter(line -> (!line.isEmpty())).allMatch(line -> line.getFirst() == ' ');
            if (isDelimColumn) {
                lines.stream().forEach(line -> {
                    try { line.removeFirst(); } catch (NoSuchElementException e) {}
                });

                long opResult = currentOp == '*' ? 1 : 0;
                while (!currentOpNums.isEmpty()) {
                    if (currentOp == '*') {
                        opResult *= currentOpNums.removeFirst();
                    } else {
                        opResult += currentOpNums.removeFirst();
                    }
                }

                result += opResult;

                boolean allLinesEmpty = lines.stream().map(line -> line.isEmpty()).allMatch(line -> line == true);
                if (allLinesEmpty) break;

                currentOpNums.clear();
                continue;
            }

            try {
                char op = lines.getLast().removeFirst();
                if (op != ' ') currentOp = op;
            } catch (NoSuchElementException e) {}

            int newOpNum = 0;
            for (int d = 0; d < lines.size() - 1; d++) {
                try {
                    char c = lines.get(d).removeFirst();
                    if (c != ' ') newOpNum = newOpNum * 10 + Character.getNumericValue(c);
                } catch (NoSuchElementException e) {}
            }
            currentOpNums.add(newOpNum);
        }

        System.out.println(result);
    }
}
