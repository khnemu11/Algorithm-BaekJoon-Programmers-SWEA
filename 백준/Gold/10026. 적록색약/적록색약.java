import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static boolean visited[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());

		char[][] board = new char[size][size];
		visited = new boolean[2][size][size];
		for (int i = 0; i < size; i++) {
			board[i] = br.readLine().toCharArray();
		}

		int rgbCount = 0, rbCount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (visited[0][i][j] == false) {
//					System.out.println("row : " + i + " col : " + j);
					rgbBfs(board, i, j, board[i][j]);
					rgbCount++;
//					System.out.println("rgbCount : " + rgbCount);
				}
				if (visited[1][i][j] == false) {
//					System.out.println("row : " + i + " col : " + j);
					rbBfs(board, i, j, board[i][j]);
					rbCount++;
//					System.out.println("rbCount : " + rbCount);
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(rgbCount);
		sb.append(" ");
		sb.append(rbCount);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	public static void rgbBfs(char[][] board, int row, int col, char color) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int size = board.length;
		visited[0][row][col] = true;

		for (int i = 0; i < 4; i++) {
			if (row + upDown[i] < 0 || row + upDown[i] >= size || col + leftRight[i] < 0
					|| col + leftRight[i] >= size) {
				continue;
			}
			if (visited[0][row + upDown[i]][col + leftRight[i]] == false
					&& board[row + upDown[i]][col + leftRight[i]] == color) {
				rgbBfs(board, row + upDown[i], col + leftRight[i], color);
			}
		}
	}

	public static void rbBfs(char[][] board, int row, int col, char color) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int size = board.length;
		visited[1][row][col] = true;

		for (int i = 0; i < 4; i++) {
			if (row + upDown[i] < 0 || row + upDown[i] >= size || col + leftRight[i] < 0
					|| col + leftRight[i] >= size) {
				continue;
			}

			if (visited[1][row + upDown[i]][col + leftRight[i]] == false) {
				if (color == 'B' && board[row + upDown[i]][col + leftRight[i]] == 'B') {
					rbBfs(board, row + upDown[i], col + leftRight[i], color);
				} else if (color != 'B' && board[row + upDown[i]][col + leftRight[i]] != 'B') {
					rbBfs(board, row + upDown[i], col + leftRight[i], color);
				}
			}
		}
	}
}
