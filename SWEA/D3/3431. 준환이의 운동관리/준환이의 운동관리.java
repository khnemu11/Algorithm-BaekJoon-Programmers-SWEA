
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int L = sc.nextInt();
			int R = sc.nextInt();
			int X = sc.nextInt();
			int result = 0;

			if (X < L) {
				result = L - X;
			} else if (X > R) {
				result = -1;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			sb.append(result);
			System.out.println(sb.toString());
		}

	}
}
