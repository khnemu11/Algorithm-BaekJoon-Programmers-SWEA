import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();

		int dp[][] = new int[a.length() + 1][b.length() + 1];

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		int row = a.length();
		int col = b.length();
		int length = dp[row][col];
		Stack<Character> stack = new Stack<>();

		while (col > 0 && row > 0) {
			if (dp[row][col] == dp[row - 1][col]) {
				row--;
			} else if (dp[row][col] == dp[row][col - 1]) {
				col--;
			} else {
				stack.add(b.charAt(col - 1));
				row--;
				col--;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(length);
		System.out.println(sb.toString());
	}
}
