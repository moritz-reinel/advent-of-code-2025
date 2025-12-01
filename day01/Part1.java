import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        var pos = new AtomicInteger(50);
        var zeros = new AtomicInteger(0);

        Files.lines(Path.of("input.txt"))
            .forEach(line -> {
                char dir = line.charAt(0);
                int mov = Integer.parseInt(line.substring(1)) % 100;

                if (dir == 'R') {
                    pos.set((pos.get() + mov) % 100);
                } else {
                    pos.set((pos.get() - mov + 100) % 100);
                }

                if (pos.get() == 0) zeros.incrementAndGet();
            });

        System.out.println(zeros.get());
    }
}
