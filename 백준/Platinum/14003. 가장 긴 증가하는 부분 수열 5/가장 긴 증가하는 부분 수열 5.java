import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int idxs[] = new int[size];
		int dp[] = new int[size];
		int length = 1;

		dp[0] = num[0];
		idxs[0] = 0;
		for (int i = 1; i < num.length; i++) {
			if (dp[length - 1] < num[i]) {
				length++;
				dp[length - 1] = num[i];
				idxs[i] = length - 1;
			} else {
				int mid = 0;
				int l = 0;
				int r = length - 1;
				while (l < r) {
					mid = (l + r) / 2;

					if (dp[mid] < num[i]) {
						l = mid + 1;
					} else {
						r = mid;
					}
				}
				dp[l] = num[i];
				idxs[i] = l;
			}
		}

		Stack<Integer> stack = new Stack<>();
		int curr = length - 1;
		for (int i = size - 1; i >= 0; i--) {
			if (idxs[i] == curr) {
				curr--;
				stack.add(num[i]);
			}
			if (curr < 0) {
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}

		System.out.println(length);
		System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
	}
}