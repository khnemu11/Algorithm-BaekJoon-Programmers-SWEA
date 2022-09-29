import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Coordinate coordinates[] = new Coordinate[3];
		double lengths[] = new double[3];
		String result = "";
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coordinates[i] = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}
		lengths[0] = Math.sqrt(
				Math.pow(coordinates[0].x - coordinates[1].x, 2) + Math.pow(coordinates[0].y - coordinates[1].y, 2));
		lengths[1] = Math.sqrt(
				Math.pow(coordinates[2].x - coordinates[1].x, 2) + Math.pow(coordinates[2].y - coordinates[1].y, 2));
		lengths[2] = Math.sqrt(
				Math.pow(coordinates[0].x - coordinates[2].x, 2) + Math.pow(coordinates[0].y - coordinates[2].y, 2));

		Arrays.sort(coordinates);
		Arrays.sort(lengths);

		if ((coordinates[1].x - coordinates[0].x)
				* (coordinates[2].y - coordinates[0].y) == (coordinates[1].y - coordinates[0].y)
						* (coordinates[2].x - coordinates[0].x)) {
			result = "X";
		} else if (lengths[0] == lengths[1] && lengths[1] == lengths[2]) {
			result = "JungTriangle";
		} else {
			if (lengths[2] == Math.sqrt(Math.pow(lengths[0], 2) + Math.pow(lengths[1], 2))) {
				result = "Jikkak2Triangle";
			} else if (lengths[2] > Math.sqrt((Math.pow(lengths[0], 2) + Math.pow(lengths[1], 2)))) {
				result = "Dunkak2Triangle";
			} else {
				result = "Yeahkak2Triangle";
			}

			if (lengths[0] != lengths[1] && lengths[1] != lengths[2] && lengths[0] != lengths[2]) {
				result = result.replace("2", "");
			}
		}
		System.out.println(result);
		br.close();
	}
}

class Coordinate implements Comparable<Coordinate> {
	int x;
	int y;

	@Override
	public int compareTo(Coordinate o) {
		if (this.x == o.x) {
			return this.y - o.y;
		}
		return this.x - o.x;
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
