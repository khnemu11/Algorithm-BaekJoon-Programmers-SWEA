import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int busStop[] = new int[5001];
			int N = sc.nextInt();

			for (int i = 0; i < N; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();

				for (int k = start; k <= end; k++) {
					busStop[k]++;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			
			int P = sc.nextInt();
			for (int i = 0; i < P; i++) {
				int no = sc.nextInt();
				sb.append(busStop[no]).append(" ");
			}

			System.out.println(sb.toString());
		}
	}

}
