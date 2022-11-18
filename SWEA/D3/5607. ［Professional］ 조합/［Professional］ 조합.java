
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int L = sc.nextInt();
			int R = sc.nextInt();
			long LFactorial = 1;
			long RFactorial = 1;
			long LMinusRFactorial = 1;

			for (int i = 1; i <= L; i++) {
				LFactorial = LFactorial * i % 1234567891;

				if (i <= R) {
					RFactorial = RFactorial * i % 1234567891;
				}
				if (i <= L - R) {
					LMinusRFactorial = LMinusRFactorial * i % 1234567891;
				}

			}
			long result = LFactorial * pow(((RFactorial * LMinusRFactorial) % 1234567891), 1234567891 - 2) % 1234567891;

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			sb.append(result);

			System.out.println(sb.toString());

		}

	}

	public static long pow(long base, int ex) {
		if (ex == 0) {
			return 1;
		} else if (ex == 1) {
			return base;
		}

		long half = pow(base, ex / 2);

		if (ex % 2 == 0) {
			return ((half % 1234567891) * (half % 1234567891)) % 1234567891;
		} else {
			return ((half % 1234567891) * (half % 1234567891) % 1234567891) * base % 1234567891;
		}
	}
}
