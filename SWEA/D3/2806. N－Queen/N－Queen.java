
import java.util.Scanner;

public class Solution {
	static boolean visited[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int length = sc.nextInt();
			visited = new boolean[length][length];
			int possible = dfs(0, length, 0);

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(possible);
			System.out.println(result.toString());
		}
	}

	public static int dfs(int curr, int depth, int total) {
		if (depth == curr) {
			return 1;
		} else {
			int count = 0;
			for (int i = 0; i < depth; i++) {
				if (!possible(curr, i)) {
					continue;
				}

				visited[curr][i] = true;
				count = count + dfs(curr + 1, depth, total);
				visited[curr][i] = false;
			}
			return count;
		}
	}

	public static boolean possible(int row, int col) {
		int upDown[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int leftRight[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int k = 0; k < upDown.length; k++) {
			int nextCol = col + leftRight[k];
			int nextRow = row + upDown[k];

			while (true) {
				if (nextCol < 0 || nextRow < 0 || nextRow >= visited.length || nextCol >= visited[0].length) {
					break;
				}
				if (visited[nextRow][nextCol]) {
					return false;
				}
				nextCol += leftRight[k];
				nextRow += upDown[k];
			}
		}

		return true;
	}
}
