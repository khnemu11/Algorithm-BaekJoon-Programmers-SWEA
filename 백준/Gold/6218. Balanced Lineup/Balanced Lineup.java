import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long maxTree[];
	static long minTree[];
	static int nums[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int Q = Integer.valueOf(st.nextToken());

		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}

		int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
		int treeSize = (int) Math.pow(2, height);
		maxTree = new long[treeSize];
		minTree = new long[treeSize];
		initMaxTree(1, 0, N - 1);
		initMinTree(1, 0, N - 1);
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken()) - 1;
			int end = Integer.valueOf(st.nextToken()) - 1;
			long max = getMax(1, start, end, 0, N - 1);
			long min = getMin(1, start, end, 0, N - 1);
			System.out.println(max - min);
		}
	}

	public static long initMaxTree(int node, int l, int r) {
		if (l == r) {
			maxTree[node] = nums[l];
		} else {
			int mid = (l + r) / 2;
			maxTree[node] = Math.max(initMaxTree(node * 2, l, mid), initMaxTree(node * 2 + 1, mid + 1, r));
		}

		return maxTree[node];
	}

	public static long getMax(int node, int start, int end, int currL, int currR) {
		if (start <= currL && currR <= end) {
			return maxTree[node];
		} else if (end < currL || currR < start) {
			return 0;
		} else {
			int mid = (currL + currR) / 2;
			return Math.max(getMax(node * 2, start, end, currL, mid), getMax(node * 2 + 1, start, end, mid + 1, currR));
		}

	}

	public static long getMin(int node, int start, int end, int currL, int currR) {
		if (start <= currL && currR <= end) {
			return minTree[node];
		} else if (end < currL || currR < start) {
			return Integer.MAX_VALUE;
		} else {
			int mid = (currL + currR) / 2;
			return Math.min(getMin(node * 2, start, end, currL, mid), getMin(node * 2 + 1, start, end, mid + 1, currR));
		}

	}

	public static long initMinTree(int node, int l, int r) {
		if (l == r) {
			minTree[node] = nums[l];
		} else {
			int mid = (l + r) / 2;
			minTree[node] = Math.min(initMinTree(node * 2, l, mid), initMinTree(node * 2 + 1, mid + 1, r));
		}

		return minTree[node];
	}
}
