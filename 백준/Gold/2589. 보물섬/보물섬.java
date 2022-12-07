import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static boolean visited[][];
	static String board[][];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		board = new String[input[0]][input[1]];
		max = 0;

		Queue<Coordinate> queue = new LinkedList<>();
		for (int i = 0; i < board.length; i++) {
			String land[] = br.readLine().split("");
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = land[j];
				if (land[j].equals("L")) {
					queue.add(new Coordinate(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Coordinate start = queue.poll();
			visited = new boolean[board.length][board[0].length];
			bfs(start);
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}

	public static void bfs(Coordinate start) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		Queue<Coordinate> queue = new LinkedList<>();
		queue.add(start);
		int length = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Coordinate curr = queue.poll();
				visited[curr.row][curr.col] = true;
				for (int k = 0; k < 4; k++) {
					Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

					if (next.row < 0 || next.col < 0 || next.row >= board.length || next.col >= board[0].length
							|| visited[next.row][next.col] || board[next.row][next.col].equals("W")) {
						continue;
					}
					visited[next.row][next.col] = true;
					queue.add(next);
				}
			}
			max = Math.max(length, max);
			length++;
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