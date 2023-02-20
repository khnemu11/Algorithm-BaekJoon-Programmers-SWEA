import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int map[][] = new int[N][N];
			boolean visited[][] = new boolean[N][N];
			int upDown[] = { 0, 1, 0, -1 };
			int leftRight[] = { 1, 0, -1, 0 };

			Coordinate curr = new Coordinate(0, 0);
			map[0][0] = 1;
			int direction = 0;
			visited[0][0] = true;
			for (int move = 2; move <= Math.pow(N, 2); move++) {
				Coordinate next = new Coordinate(curr.row + upDown[direction], curr.col + leftRight[direction]);
				if (next.row < 0 || next.col < 0 || next.row >= N || next.col >= N || visited[next.row][next.col]) {
					direction = direction + 1 >= 4 ? 0 : direction + 1;
					next = new Coordinate(curr.row + upDown[direction], curr.col + leftRight[direction]);
				}
				visited[next.row][next.col] = true;
				map[next.row][next.col] = move;
				curr = next;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append("\n");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
			}

			bw.write(sb.toString());
		}
		bw.flush();
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