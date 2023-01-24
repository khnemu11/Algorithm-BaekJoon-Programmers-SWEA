import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	static String exp[];
	static boolean visited[];
	static int nums[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		exp = br.readLine().split(" ");
		visited = new boolean[10];
		nums = new int[exp.length + 1];

		for (int num = 9; num >= 1; num--) {
			nums[0] = num;
			visited[num] = true;
			if (selectMax(0)) {
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i <= exp.length; i++) {
					sb.append(nums[i]);

				}
				System.out.println(sb.toString());
				break;
			}
			visited[num] = false;
		}

		visited = new boolean[10];

		for (int num = 0; num < 10; num++) {
			nums[0] = num;
			visited[num] = true;
			if (selectMin(0)) {
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i <= exp.length; i++) {
					sb.append(nums[i]);
				}

				System.out.println(sb.toString());
				break;
			}
			visited[num] = false;
		}
	}

	public static boolean selectMax(int curr) {
		if (curr == exp.length) {
			return true;
		} else {
			for (int i = 9; i >= 0; i--) {
				if (visited[i]) {
					continue;
				} else if (exp[curr].equals(">") && nums[curr] < i) {
					continue;
				} else if (exp[curr].equals("<") && nums[curr] > i) {
					continue;
				}

				visited[i] = true;
				nums[curr + 1] = i;
				if (selectMax(curr + 1)) {
					return true;
				} else {
					visited[i] = false;
				}
			}
			return false;
		}

	}

	public static boolean selectMin(int curr) {
		if (curr == exp.length) {
			return true;
		} else {
			for (int i = 0; i < 10; i++) {
				if (visited[i]) {
					continue;
				} else if (exp[curr].equals(">") && nums[curr] < i) {
					continue;
				} else if (exp[curr].equals("<") && nums[curr] > i) {
					continue;
				}

				visited[i] = true;
				nums[curr + 1] = i;
				if (selectMin(curr + 1)) {
					return true;
				} else {
					visited[i] = false;
				}
			}
			return false;
		}

	}
}
