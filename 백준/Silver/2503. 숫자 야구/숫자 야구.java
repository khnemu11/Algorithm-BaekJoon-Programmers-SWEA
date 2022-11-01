import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static ArrayList<Condition> conditions;
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		conditions = new ArrayList<>();
		visited = new boolean[10];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			conditions.add(new Condition(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken())));
		}

		int count = dfs(3, 0);

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int dfs(int depth, int curr) {
		if (depth == curr) {
//			System.out.println();
			StringBuilder sb = new StringBuilder();

			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}

			String candidate = sb.toString();

			for (int i = 2; i >= 0; i--) {
				stack.add(Integer.valueOf(candidate.charAt(i) - '0'));
			}
//			System.out.println(stack.toString());
			for (Condition condition : conditions) {

//				System.out.println(candidate + " vs " + condition.value);

				int strike = 0;
				int ball = 0;

				for (int i = 0; i < 3; i++) {
					String value = String.valueOf(condition.value);

					if (value.contains(String.valueOf(candidate.charAt(i)))) {
						if (value.charAt(i) == candidate.charAt(i)) {
							strike++;
						} else {
							ball++;
						}
					}
				}

				if (strike != condition.strike || ball != condition.ball) {
					return 0;
				}
			}

			return 1;
		} else {
			int count = 0;
			for (int i = 1; i < 10; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				stack.add(i);
				count += dfs(depth, curr + 1);
				visited[i] = false;
				stack.pop();

			}
			return count;
		}
	}

}

class Condition {
	int value;
	int strike;
	int ball;

	public Condition(int value, int strike, int ball) {
		this.value = value;
		this.strike = strike;
		this.ball = ball;
	}
}