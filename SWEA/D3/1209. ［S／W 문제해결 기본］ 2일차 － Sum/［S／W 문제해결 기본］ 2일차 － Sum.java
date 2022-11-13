import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		for (int test_case = 1; test_case <= 10; test_case++) {
			int no = sc.nextInt();

			int board[][] = new int[100][100];

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int max = 0;

			// 행

			for (int i = 0; i < 100; i++) {
				max = Math.max(max, Arrays.stream(board[i]).sum());
			}
			// 열

			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += board[i][j];
				}
				max = Math.max(sum, max);
			}

			// 왼 대각
			for (int j = 99; j >= 0; j--) {
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += board[i][j];
				}

				max = Math.max(sum, max);
			}
			// 오른대각

			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int i = 99; i >= 0; i--) {
					sum += board[i][j];
				}

				max = Math.max(sum, max);
			}
			StringBuilder result = new StringBuilder("#");
			result.append(no).append(" ").append(max);
			System.out.println(result.toString());
		}
	}
}
