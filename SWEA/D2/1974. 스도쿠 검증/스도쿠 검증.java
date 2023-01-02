import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = Integer.valueOf(sc.nextLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int sudoku[][] = new int[9][9];

			for (int i = 0; i < 9; i++) {
				sudoku[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " ");
			if (isValid(sudoku)) {
				sb.append("1");
			} else {
				sb.append("0");
			}

			System.out.println(sb.toString());
		}
	}

	public static boolean isValid(int sudoku[][]) {
		boolean visited[] = new boolean[9];

		for (int i = 0; i < 9; i++) {
			visited = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (visited[sudoku[i][j] - 1]) {
					return false;
				}
				visited[sudoku[i][j] - 1] = true;
			}
		}

		for (int i = 0; i < 9; i++) {
			visited = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (visited[sudoku[j][i] - 1]) {
					return false;
				}
				visited[sudoku[j][i] - 1] = true;
			}
		}
		int upDown[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int leftRight[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

		for (int i = 1; i < 9; i += 3) {
			for (int j = 1; j < 9; j += 3) {
				visited = new boolean[9];
				for (int k = 0; k < upDown.length; k++) {
					if (visited[sudoku[i + upDown[k]][j + leftRight[k]] - 1]) {
						return false;
					}
					visited[sudoku[i + upDown[k]][j + leftRight[k]] - 1] = true;
				}
			}
		}

		return true;
	}

}
