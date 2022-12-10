import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static boolean visited[];
	static char board[][];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int height = input[0];
		int width = input[1];

		board = new char[height][width];
		visited = new boolean[26];
		max = 0;
		for (int i = 0; i < height; i++) {
			board[i] = br.readLine().toCharArray();
		}

		visited[board[0][0] - 'A'] = true;
		dfs(new Coordinate(0, 0), 1);

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static void dfs(Coordinate curr, int sum) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		max = Math.max(sum, max);

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= board.length || next.col >= board[0].length
					|| visited[board[next.row][next.col] - 'A']) {
				continue;
			} else {
				visited[board[next.row][next.col] - 'A'] = true;
				dfs(next, sum + 1);
				visited[board[next.row][next.col] - 'A'] = false;
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