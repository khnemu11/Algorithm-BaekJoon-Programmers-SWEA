import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char exp[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		exp = br.readLine().toCharArray();

		select(0, exp[0] - '0');
		System.out.println(max);

	}

	public static void select(int curr, int sum) {
		if (curr + 4 < exp.length) {
			select(curr + 4, calc(sum, calc(exp[curr + 2] - '0', exp[curr + 4] - '0', exp[curr + 3]), exp[curr + 1]));
		}
		if (curr + 2 < exp.length) {
			select(curr + 2, calc(sum, exp[curr + 2] - '0', exp[curr + 1]));
		} else {
			max = Math.max(max, sum);
		}

	}

	public static int calc(int a, int b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else {
			return a * b;
		}
	}
}
