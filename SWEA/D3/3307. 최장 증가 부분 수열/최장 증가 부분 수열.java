
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int length = sc.nextInt();
			int max = 0;
			Stack<Integer> stack = new Stack<>();
			for (int i = 1; i <= length; i++) {
				int num = sc.nextInt();
				Stack<Integer> rest = new Stack<>();
				while (!stack.empty() && stack.peek() >= num) {
					rest.add(stack.pop());
				}
				stack.add(num);
				if (!rest.isEmpty()) {
					rest.pop();
				}
				while (!rest.isEmpty()) {
					stack.add(rest.pop());
				}
				max = Math.max(max, stack.size());
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(max);
			System.out.println(result.toString());
		}
	}

}
