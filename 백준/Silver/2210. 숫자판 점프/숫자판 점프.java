import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int board[][];
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		board = new int[5][5];
		for (int row = 0; row < board.length; row++) {
			board[row] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				dfs(new Coordinate(row, col), 0, new StringBuilder());
			}
		}
		bw.write(String.valueOf(set.size()) + "\n");
		bw.flush();
	}

	public static void dfs(Coordinate curr, int depth, StringBuilder sb) {
		if (depth == 6) {
			set.add(sb.toString());
		} else {
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };

			for (int i = 0; i < 4; i++) {
				Coordinate next = new Coordinate(curr.row + upDown[i], curr.col + leftRight[i]);

				if (next.row < 0 || next.col < 0 || next.row >= 5 || next.col >= 5) {
					continue;
				}
				sb.append(board[next.row][next.col]);
				dfs(next, depth + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
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
