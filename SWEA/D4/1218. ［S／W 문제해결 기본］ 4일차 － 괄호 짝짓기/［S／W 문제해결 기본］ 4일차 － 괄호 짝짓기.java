import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = 10;

		HashMap<Character, Character> map = new HashMap<>();

		map.put(')', '(');
		map.put('}', '{');
		map.put('>', '<');
		map.put(']', '[');

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			char str[] = br.readLine().toCharArray();
			boolean isValid = true;
			Stack<Character> stack = new Stack<>();

			for (char basket : str) {
				if (map.get(basket) == null) {
					stack.add(basket);
				} else if (map.get(basket) == stack.peek()) {
					stack.pop();
				} else {
					isValid = false;
					break;
				}
			}

			if (!stack.isEmpty()) {
				isValid = false;
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(test_case).append(" ");

			if (isValid) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}

			bw.write(sb.toString());
		}

		bw.flush();
	}
}