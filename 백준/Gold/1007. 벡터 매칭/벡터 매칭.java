import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static ArrayList<Coordinate> coordinateList;
	static double min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 0; test_case < T; test_case++) {
			int size = Integer.valueOf(br.readLine());
			coordinateList = new ArrayList<>();
			visited = new boolean[size];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < size; i++) {
				int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
				coordinateList.add(new Coordinate(row[0], row[1]));
			}

			dfs(0, size / 2, 0);
			bw.write(String.valueOf(min));
			bw.newLine();
		}

		bw.flush();
	}

	public static void dfs(int curr, int depth, int start) {
		if (curr == depth) {
			Coordinate sumVector = new Coordinate(0, 0);
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					sumVector.row += coordinateList.get(i).row;
					sumVector.col += coordinateList.get(i).col;
				} else {
					sumVector.row -= coordinateList.get(i).row;
					sumVector.col -= coordinateList.get(i).col;
				}
			}

			double direction = Math.sqrt(Math.pow(sumVector.row, 2) + Math.pow(sumVector.col, 2));
			min = Math.min(min, direction);

		} else {
			for (int i = start; i < coordinateList.size(); i++) {
				if (visited[i]) {
					continue;
				}
				visited[i] = true;
				dfs(curr + 1, depth, i + 1);
				visited[i] = false;
			}
		}
	}
}

class Coordinate {
	int row;
	int col;

	Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}
