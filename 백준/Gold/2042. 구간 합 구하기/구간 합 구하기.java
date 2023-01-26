import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long sumTree[];
	static long num[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());
		num = new long[N];

		for (int i = 0; i < N; i++) {
			num[i] = Long.valueOf(br.readLine());
		}

		int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
		int treeSize = (int) Math.pow(2, height);

		sumTree = new long[treeSize];
		initTree(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.valueOf(st.nextToken());

			if (command == 1) {
				int idx = Integer.valueOf(st.nextToken()) - 1;
				long val = Long.valueOf(st.nextToken());
				long differ = val - num[idx];
				num[idx] = val;
				updateTree(1, 0, N - 1, idx, differ);
			} else {
				int l = Integer.valueOf(st.nextToken()) - 1;
				int r = Integer.valueOf(st.nextToken()) - 1;
				System.out.println(getSum(1, 0, N - 1, l, r));
			}
		}

	}

	public static long initTree(int node, int l, int r) {
		if (l == r) {
			sumTree[node] = num[l];
		} else {
			int mid = (l + r) / 2;
			sumTree[node] = initTree(node * 2, l, mid) + initTree(node * 2 + 1, mid + 1, r);
		}

		return sumTree[node];
	}

	public static long getSum(int node, int start, int end, int l, int r) {
		if (end < l || r < start) {
			return 0;
		}
		if (l <= start && end <= r) {
			return sumTree[node];
		} else {
			int mid = (start + end) / 2;
			return getSum(node * 2, start, mid, l, r) + getSum(node * 2 + 1, mid + 1, end, l, r);
		}

	}

	public static void updateTree(int node, int l, int r, int idx, long differ) {
		if (idx < l || idx > r) {
			return;
		}
		sumTree[node] = sumTree[node] + differ;

		if (l == r) {
			return;
		}

		int mid = (l + r) / 2;

		updateTree(node * 2, l, mid, idx, differ);
		updateTree(node * 2 + 1, mid + 1, r, idx, differ);
	}
}
