import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static Node tree[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			tree = new Node[N + 1];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.valueOf(st.nextToken());
				String val = st.nextToken();
				int left = 0, right = 0;

				if (st.hasMoreTokens()) {
					left = Integer.valueOf(st.nextToken());
				}
				if (st.hasMoreTokens()) {
					right = Integer.valueOf(st.nextToken());
				}

				tree[idx] = new Node(val, left, right);
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");

			if (canCalc(1)) {
				sb.append(1);
			} else {
				sb.append(0);
			}

			sb.append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
	}

	public static boolean canCalc(int idx) {
		String op = "*+-/";
		if (idx == 0) {
			return false;
		} else if (op.contains(tree[idx].val)) {
			return canCalc(tree[idx].left) && canCalc(tree[idx].right);
		} else {
			if (tree[idx].left == 0 && tree[idx].right == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}

class Node {
	String val;
	int left;
	int right;

	public Node(String val, int left, int right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}