import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static StringBuilder sb;
	static Node nodes[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = 10;
		for (int testcase = 1; testcase <= TC; ++testcase) {
			sb = new StringBuilder();

			int N = Integer.valueOf(br.readLine());
			nodes = new Node[N + 1];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.valueOf(st.nextToken());
				String val = st.nextToken();
				if (nodes[idx] == null) {
					nodes[idx] = new Node();
				}
				nodes[idx].val = val;

				if (st.hasMoreTokens()) {
					int left = Integer.valueOf(st.nextToken());

					if (nodes[idx].left == null) {
						nodes[left] = new Node();
					}

					nodes[idx].left = nodes[left];

				}
				if (st.hasMoreTokens()) {
					int right = Integer.valueOf(st.nextToken());

					if (nodes[idx].right == null) {
						nodes[right] = new Node();
					}

					nodes[idx].right = nodes[right];
				}
			}
			midTravelsal(nodes[1]);

			System.out.println("#" + testcase + " " + sb.toString());
		}

		br.close();
	}

	public static void midTravelsal(Node curr) {
		if (curr == null) {
			return;
		}

		midTravelsal(curr.left);
		sb.append(curr.val);
		midTravelsal(curr.right);
	}
}

class Node {
	String val;
	Node left;
	Node right;

	public Node() {
	}

	public Node(String val) {
		this.val = val;
	}
}