import java.io.IOException;
import java.nio.file.*;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        long result = Files.lines(Path.of("input.txt"))
            .mapToLong(line -> {
                var digits = line.chars().map(c -> c - (int) '0').toArray();
                
                int firstIdx = -1;
                int firstNum = 0;
                for (int i = 0; i < digits.length - 1; i++) {
                    if (digits[i] > firstNum) {
                        firstNum = digits[i];
                        firstIdx = i;
                    }
                }

                int secondNum = 0;
                for (int i = firstIdx + 1; i < digits.length; i++) {
                    secondNum = Math.max(secondNum, digits[i]);
                }

                return firstNum * 10 + secondNum;
            })
            .sum();

        System.out.println(result);
    }
}
