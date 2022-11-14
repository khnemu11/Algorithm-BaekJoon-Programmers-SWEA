
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int num = sc.nextInt();
			int under = sc.nextInt();
			int ex = sc.nextInt();

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(pow(under, ex));
			System.out.println(result.toString());
		}
	}

	public static int pow(int under, int ex) {

		if (ex == 1) {
			return under;
		} else if (ex == 0) {
			return 1;
		}

		int half = pow(under, ex / 2);

		if (ex % 2 == 0) {
			return half * half;
		} else {
			return half * half * under;
		}

	}
}
