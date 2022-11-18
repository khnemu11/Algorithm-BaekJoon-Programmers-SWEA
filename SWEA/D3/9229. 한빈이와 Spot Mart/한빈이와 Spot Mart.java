
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int limit = sc.nextInt();

			int weight[] = new int[N];

			for (int i = 0; i < N; i++) {
				weight[i] = sc.nextInt();
			}

			Arrays.sort(weight);

			int l = N - 2;
			int r = N - 1;
			int max = -1;

			while (r > 0) {
				if (weight[l] + weight[r] <= limit && weight[l] + weight[r] > max) {
					max = weight[l] + weight[r];
					r--;
					l = r - 1;
                    continue;
				}

				l--;

				if (l == -1) {
					r--;
					l = r - 1;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(max);

			System.out.println(sb.toString());

		}

	}

}
