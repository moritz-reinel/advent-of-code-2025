import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        var pos = new AtomicInteger(50);
        var zeros = new AtomicInteger(0);

        Files.lines(Path.of("input.txt"))
            .forEach(line -> {
                char dir = line.charAt(0);
                int mov = Integer.parseInt(line.substring(1));

                for (int i = 0; i < mov; i++) {
                    if (dir == 'R') {
                        pos.set(pos.incrementAndGet() % 100);
                    } else {
                        pos.set((pos.decrementAndGet() + 100) % 100);
                    }

                    if (pos.get() == 0) zeros.incrementAndGet();
                }
            });

        System.out.println(zeros.get());
    }
}
