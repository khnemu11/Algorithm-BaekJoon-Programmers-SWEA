
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int num = sc.nextInt();

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(num / 3);
			System.out.println(result.toString());
		}
	}
}
