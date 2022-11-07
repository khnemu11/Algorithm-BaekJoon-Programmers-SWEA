import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int input[] = new int[4];
		HashSet<Rect> set = new HashSet<>();

		for (int i = 0; i < 4; i++) {
			input = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

			Rect rect = new Rect(input[0], input[1], input[2], input[3]);
			for (int x = rect.startX; x < rect.endX; x++) {
				for (int y = rect.startY; y < rect.endY; y++) {
					set.add(new Rect(x, x + 1, y, y + 1));
				}
			}
		}

		bw.write(String.valueOf(set.size()));
		bw.newLine();
		br.close();
		bw.close();
	}
}

class Rect {
	int startX;
	int startY;
	int endX;
	int endY;

	public Rect(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	@Override
	public String toString() {
		return "Rect [startX=" + startX + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Rect) {
			Rect curr = (Rect) o;
			if (this.toString().equals(curr.toString())) {
				return true;
			}
		}

		return false;

	}
}