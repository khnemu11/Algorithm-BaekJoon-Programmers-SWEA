import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int map[][];
	static int dp[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		int width = input[1];
		int height = input[0];
		map = new int[height][width];
		dp = new int[height][width];
		visited = new boolean[height][width];
		dp[0][0] = 1;
		visited[0][0] = true;

		for (int i = 0; i < height; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}

//		for (int row = 0; row < height; row++) {
//			for (int col = 0; col < width; col++) {
//				if (row == 0 && col == 0 || visited[row][col]) {
//					continue;
//				}
//				dp[row][col] = dfs(new Coordinate(row, col));
//			}
//		}
		dp[height - 1][width - 1] = dfs(new Coordinate(height - 1, width - 1));

		System.out.println(dp[height - 1][width - 1]);
	}

	public static int dfs(Coordinate curr) {
		if (visited[curr.row][curr.col]) {
			return dp[curr.row][curr.col];
		} else {
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };

			for (int k = 0; k < upDown.length; k++) {
				Coordinate before = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
				if (before.row < 0 || before.col < 0 || before.row >= map.length || before.col >= map[0].length) {
					continue;
				}
				if (map[before.row][before.col] <= map[curr.row][curr.col]) {
					continue;
				}

				dp[curr.row][curr.col] += dfs(before);
			}
			visited[curr.row][curr.col] = true;
			return dp[curr.row][curr.col];
		}
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