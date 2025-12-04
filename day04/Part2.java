import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class Part2 {
	public static void main(String[] args) throws IOException {
		var field = Files.lines(Path.of("input.txt"))
			.map(line -> new ArrayList<>(line.chars().mapToObj(c -> (char) c).toList()))
			.toList();

		int result = 0, prevResult = -1;
		while (result > prevResult) {
			prevResult = result;

			for (int y = 0; y < field.size(); y++) {
				for (int x = 0; x < field.getFirst().size(); x++) {
					if (field.get(y).get(x) != '@') continue;

					int surrounding = 0;
					for (int dy = -1; dy <= 1; dy++) {
						for (int dx = -1; dx <= 1; dx++) {
							int oy = y + dy, ox = x + dx;
							if (oy < 0 || oy >= field.size() || ox < 0 || ox >= field.getFirst().size() || (y == oy && x == ox))
								continue;

							char other = field.get(oy).get(ox);
							if (other == '@') surrounding++;
						}
					}

					if (surrounding < 4) {
						field.get(y).set(x, '.');
						result++;
					}
				}
			}
		}

		System.out.println(result);
	}
}
