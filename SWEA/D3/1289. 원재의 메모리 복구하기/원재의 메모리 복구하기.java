import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuilder curr = new StringBuilder();
			String goal = sc.next();

			for (int i = 0; i < goal.length(); i++) {
				curr.append("0");
			}

			int count = 0;

			for (int i = 0; i < curr.length(); i++) {
				if (goal.charAt(i) == curr.charAt(i)) {
					continue;
				}

				char target = goal.charAt(i);
				StringBuilder next = new StringBuilder(curr.subSequence(0, i));

				for (int j = i; j < curr.length(); j++) {
					next.append(target);
				}

				curr = next;
				count++;
			}

			StringBuilder result = new StringBuilder("#");
			result.append(test_case).append(" ").append(count);
			System.out.println(result.toString());
		}
	}
}
