import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int maxTree[];
	static int minTree[];
	static int num[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		num = new int[N];

		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.valueOf(br.readLine());
		}

		int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
		int treeSize = (int) Math.pow(2, treeHeight);

		maxTree = new int[treeSize];
		minTree = new int[treeSize];
		Arrays.fill(minTree, Integer.MAX_VALUE);
		initMaxTree(1, 0, num.length - 1);
		initMinTree(1, 0, num.length - 1);

		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			System.out.print(
					getMin(1, 0, N - 1, start - 1, end - 1) + " " + getMax(1, 0, N - 1, start - 1, end - 1) + "\n");
		}
	}

	public static int initMaxTree(int node, int l, int r) {
		if (l == r) {
			maxTree[node] = num[l];
			return maxTree[node];
		} else {
			int mid = (l + r) / 2;

			maxTree[node] = Math.max(initMaxTree(node * 2, l, mid), initMaxTree(node * 2 + 1, mid + 1, r));

			return maxTree[node];
		}
	}

	public static int initMinTree(int node, int l, int r) {
		if (l == r) {
			minTree[node] = num[l];
			return minTree[node];
		} else {
			int mid = (l + r) / 2;

			minTree[node] = Math.min(initMinTree(node * 2, l, mid), initMinTree(node * 2 + 1, mid + 1, r));

			return minTree[node];
		}
	}

	public static int getMax(int node, int start, int end, int l, int r) {
//		System.out
//				.println("node : " + node + " start : " + start + " end : " + end + " find l : " + l + " find r " + r);
		if (l > end || r < start) {
			return 0;
		} else if (l <= start && end <= r) {
			return maxTree[node];
		} else {
			int mid = (start + end) / 2;
			return Math.max(getMax(node * 2, start, mid, l, r), getMax(node * 2 + 1, mid + 1, end, l, r));
		}
	}

	public static int getMin(int node, int start, int end, int l, int r) {
//		System.out
//				.println("node : " + node + " start : " + start + " end : " + end + " find l : " + l + " find r " + r);
		if (l > end || r < start) {
			return Integer.MAX_VALUE;
		} else if (l <= start && end <= r) {
			return minTree[node];
		} else {
			int mid = (start + end) / 2;
			return Math.min(getMin(node * 2, start, mid, l, r), getMin(node * 2 + 1, mid + 1, end, l, r));
		}
	}
}
