import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static char board[][];
	static boolean visited[][];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		board = new char[size][size];
		visited = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			board[i] = br.readLine().toCharArray();
		}

		Coordinate curr = new Coordinate(0, 0);

		while (curr.row < size && curr.col < size) {
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };

			visited[curr.row][curr.col] = true;

			for (int k = 0; k < 4; k++) {
				Coordinate change = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

				if (change.row < 0 || change.col < 0 || change.row >= size || change.col >= size
						|| visited[change.row][change.col]) {
					continue;
				}

				char temp = board[curr.row][curr.col];
				board[curr.row][curr.col] = board[change.row][change.col];
				board[change.row][change.col] = temp;

				countMax(curr);
				countMax(change);

				temp = board[curr.row][curr.col];
				board[curr.row][curr.col] = board[change.row][change.col];
				board[change.row][change.col] = temp;

			}

			curr.col++;
			if (curr.col == size) {
				curr.row++;
				curr.col = 0;
			}
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
		br.close();
	}

	public static void countMax(Coordinate curr) {
		int count = 1;
		// 가로
		// 왼쪽
		// 오른쪽
		int leftRight[] = { -1, 1 };
		int upDown[] = { -1, 1 };

		for (int k = 0; k < leftRight.length; k++) {
			int move = 1;

			while (true) {
				if (curr.col + leftRight[k] * move < 0 || curr.col + leftRight[k] * move >= board[0].length
						|| board[curr.row][curr.col + leftRight[k] * move] != board[curr.row][curr.col]) {
					break;
				}
				move++;
				count++;
			}
		}

		max = Math.max(max, count);

		// 세로
		count = 1;
		for (int k = 0; k < upDown.length; k++) {
			int move = 1;

			while (true) {
				if (curr.row + upDown[k] * move < 0 || curr.row + upDown[k] * move >= board.length
						|| board[curr.row + upDown[k] * move][curr.col] != board[curr.row][curr.col]) {
					break;
				}
				move++;
				count++;
			}
		}
		max = Math.max(max, count);
	}
}

class Coordinate {
	int row;
	int col;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}