import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int forest[][];
	static int dp[][];
	static boolean visited[][];
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		forest = new int[size][size];
		dp = new int[size][size];
		visited = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			forest[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			Arrays.fill(dp[i], 1);
		}

		max = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (visited[i][j]) {
					continue;
				}
				eat(new Coordinate(i, j));
			}
		}
		System.out.println(max);
	}

	public static void eat(Coordinate curr) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		visited[curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate before = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (before.row < 0 || before.col < 0 || before.row >= forest.length || before.col >= forest.length
					|| forest[curr.row][curr.col] <= forest[before.row][before.col]) {
				continue;
			} else if (visited[before.row][before.col]) {
				dp[curr.row][curr.col] = Math.max(dp[before.row][before.col] + 1, dp[curr.row][curr.col]);
			} else {
				eat(before);
				dp[curr.row][curr.col] = Math.max(dp[before.row][before.col] + 1, dp[curr.row][curr.col]);
			}
		}

		max = Math.max(dp[curr.row][curr.col], max);
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
