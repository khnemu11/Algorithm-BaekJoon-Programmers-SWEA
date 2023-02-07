import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution{
	static int upDown[] = { 0, 0, -1 };
	static int leftRight[] = { -1, 1, 0 };
	static int map[][];
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int no = Integer.valueOf(br.readLine());

			map = new int[100][100];
			Coordinate goal = null;
			for (int i = 0; i < map.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = Integer.valueOf(st.nextToken());

					if (map[i][j] == 2) {
						goal = new Coordinate(i, j);
					}
				}
			}
			visited = new boolean[100][100];
			Coordinate start = move(goal);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(start.col);

			bw.write(sb.toString() + "\n");
		}

		bw.flush();
	}

	public static Coordinate move(Coordinate start) {
		Coordinate curr = start;

		while (curr.row > 0) {
			visited[curr.row][curr.col] = true;
			for (int k = 0; k < upDown.length; k++) {
				Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
				if (!isValid(next)) {
					continue;
				}
				curr = next;
				break;
			}
		}

		return curr;
	}

	public static boolean isValid(Coordinate coord) {
		if (coord.row < 0 || coord.row >= map.length || coord.col < 0 || coord.col >= map[0].length
				|| visited[coord.row][coord.col] || map[coord.row][coord.col] == 0) {
			return false;
		}
		return true;
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