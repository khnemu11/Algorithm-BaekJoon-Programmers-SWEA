import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static boolean visited[][];
	static int maze[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			br.readLine();
			maze = new int[16][16];
			visited = new boolean[16][16];
			Coordinate start = null, end = null;

			for (int i = 0; i < maze.length; i++) {
				char row[] = br.readLine().toCharArray();
				for (int j = 0; j < maze[0].length; j++) {
					maze[i][j] = row[j] - '0';

					if (maze[i][j] == 2) {
						start = new Coordinate(i, j);
					} else if (maze[i][j] == 3) {
						end = new Coordinate(i, j);
					}
				}
			}
			StringBuilder sb = new StringBuilder();

			sb.append("#" + test_case + " " + isHasRoute(start, end));
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
	}

	public static int isHasRoute(Coordinate curr, Coordinate end) {
		if (curr.row == end.row && curr.col == end.col) {
			return 1;
		}

		int upDown[] = { -1, 0, 0, 1 };
		int leftRight[] = { 0, -1, 1, 0 };
		visited[curr.row][curr.col] = true;
		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= maze.length || next.col >= maze.length
					|| visited[next.row][next.col] || maze[next.row][next.col] == 1) {
				continue;
			}

			visited[next.row][next.col] = true;
			if (isHasRoute(next, end) == 1) {
				return 1;
			}
			visited[next.row][next.col] = false;
		}
		return 0;
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