import java.io.IOException;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			sc.nextInt();

			int arr[][] = new int[100][100];
			int max = 0;
			for (int i = 0; i < 100; i++) {
				int rowSum = 0;
				for (int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
					rowSum += arr[i][j];
				}
				max = Math.max(rowSum, max);
			}

			for (int j = 0; j < 100; j++) {
				int colSum = 0;
				for (int i = 0; i < 100; i++) {
					colSum += arr[i][j];
				}
				max = Math.max(colSum, max);
			}
			int leftDiagSum = 0;
			for (int i = 0; i < 100; i++) {
				leftDiagSum += arr[i][i];
			}
			max = Math.max(leftDiagSum, max);

			int rightDiagSum = 0;
			for (int i = 99; i >= 0; i--) {
				rightDiagSum += arr[99 - i][i];
			}
			max = Math.max(rightDiagSum, max);

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " " + max);
			System.out.println(sb.toString());
		}
	}

}
