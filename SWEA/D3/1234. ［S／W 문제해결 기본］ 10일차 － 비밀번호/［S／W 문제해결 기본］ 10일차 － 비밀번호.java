import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int length = sc.nextInt();
			char plainText[] = sc.next().toCharArray();
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < length; i++) {
				if (stack.isEmpty()) {
					stack.add(plainText[i]);
				} else if (stack.peek() == plainText[i]) {
					stack.pop();
				} else {
					stack.add(plainText[i]);
				}
			}

			StringBuilder password = new StringBuilder();

			while (!stack.isEmpty()) {
				password.append(stack.pop());
			}

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ").append(password.reverse().toString());
			System.out.println(result.toString());
		}
	}

}
