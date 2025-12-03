import java.io.IOException;
import java.nio.file.*;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        long result = Files.lines(Path.of("input.txt"))
            .mapToLong(line -> {
                var digits = line.chars().map(c -> c - (int) '0').toArray();

                int pos = 0;
                long num = 0;

                for (int i = 0; i < 12; i++) {
                    int newDigit = -1;
                    for (int p = pos; p < digits.length - (12 - i - 1); p++) {
                        if (digits[p] > newDigit) {
                            newDigit = digits[p];
                            pos = p + 1;
                        }
                    }
                    num = num * 10 + newDigit;
                }

                return num;
            })
            .sum();

        System.out.println(result);
    }
}
