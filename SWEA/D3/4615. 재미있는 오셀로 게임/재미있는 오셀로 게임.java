import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
	Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
		StringBuilder result = new StringBuilder("#" + test_case + " ");
			int N = sc.nextInt();
			int M = sc.nextInt();

			int board[][] = new int[N][N];

			board[N / 2 - 1][N / 2 - 1] = 2;
			board[N / 2 - 1][N / 2] = 1;
			board[N / 2][N / 2 - 1] = 1;
			board[N / 2][N / 2] = 2;

			for (int k = 0; k < M; k++) {
				int row = sc.nextInt() - 1;
				int col = sc.nextInt() - 1;
				int color = sc.nextInt(); // 1 흑, 2 백
				board[row][col] = color;

				int upDown[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
				int leftRight[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

				for (int i = 0; i < upDown.length; i++) {
					int nextRow = upDown[i] + row;
					int nextCol = leftRight[i] + col;
					while (true) {
						if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N
								|| board[nextRow][nextCol] == 0) {
							nextRow = row;
							nextCol = col;
							break;
						} else if (board[nextRow][nextCol] == color) {
							break;
						}
						nextRow += upDown[i];
						nextCol += leftRight[i];
					}
					while (true) {
						if (nextRow == row && nextCol == col) {
							break;
						}
						board[nextRow][nextCol] = color;
						nextRow -= upDown[i];
						nextCol -= leftRight[i];
					}
				}
			}
			int blackCnt = 0;
			int whiteCnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 1) {
						blackCnt++;
					} else if (board[i][j] == 2) {
						whiteCnt++;
					}
				}
			}

			result.append(blackCnt + " " + whiteCnt);
			System.out.println(result.toString());
        }
	}
}