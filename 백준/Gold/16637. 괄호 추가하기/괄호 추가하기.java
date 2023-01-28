import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static boolean visited[];
	static int nums[];
	static char ops[];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		char[] input = br.readLine().toCharArray();
		int numSize = size / 2 + 1;

		visited = new boolean[numSize];
		nums = new int[numSize];
		ops = new char[numSize];

		for (int i = 0; i < input.length; i += 2) {
			nums[i / 2] = input[i] - '0';
		}
		for (int i = 1; i < input.length; i += 2) {
			ops[i / 2] = input[i];
		}

		select(0);
		System.out.println(max);

	}

	public static void select(int curr) {
		if (curr >= ops.length - 1) {
			Queue<Integer> numQ = new LinkedList<>();
			Queue<Character> opQ = new LinkedList<>();

			for (int i = 0; i < nums.length; i++) {
				if (visited[i]) {
					int val = calc(nums[i], nums[i + 1], ops[i]);
					numQ.add(val);
					i++;
					opQ.add(ops[i]);
				} else {
					numQ.add(nums[i]);
					opQ.add(ops[i]);
				}
			}
			int sum = numQ.poll();

			while (!numQ.isEmpty()) {
				sum = calc(sum, numQ.poll(), opQ.poll());
			}
			max = Math.max(max, sum);

			return;
		}

		visited[curr] = true;
		select(curr + 2);
		visited[curr] = false;

		select(curr + 1);

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
