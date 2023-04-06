import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 1) 유니온 파인드로 루트노드 탐색
 * 2) 노드별 자식 개수 구하기 (dfs)
 * 3) 노드별 좌표 구하기(dfs)
 * 		왼쪽 좌표 = 부모 좌표 +왼쪽 좌표의 오른쪽 노드의 개수 - 1; 
 * 		오른쪽 좌표 = 부모 좌표 +오른쪽 좌표의 왼쪽 노드의 개수 + 1; 
 * 4) 깊이 별 너비 구하기 (bfs)
 * */

public class Main {
	static int parents[];
	static Node tree[];
	static int minDepth;
	static int maxWidth;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		tree = new Node[N + 1];
		parents = new int[N + 1];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.valueOf(st.nextToken());
			int left = Integer.valueOf(st.nextToken());
			int right = Integer.valueOf(st.nextToken());

			tree[parent] = new Node();
			tree[parent].left = left;
			tree[parent].right = right;

			union(parent, left);
			union(parent, right);
		}

		int root = getParent(1);

		getCnt(root);
		setCol(root);
		getMaxWidth(root);

		System.out.println(minDepth + " " + maxWidth);
	}

	private static void getMaxWidth(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		int depth = 1;
		while (!q.isEmpty()) {
			int loop = q.size();
			int maxCol = -100_000;
			int minCol = 100_000;

			while (loop-- > 0) {
				int curr = q.poll();
				if (curr == -1) {
					continue;
				}
				int col = tree[curr].col;

				maxCol = Math.max(maxCol, col);
				minCol = Math.min(minCol, col);
				q.add(tree[curr].left);
				q.add(tree[curr].right);
			}
			int width = maxCol - minCol + 1;
			if (maxWidth < width) {
				maxWidth = width;
				minDepth = depth;
			}

			depth++;
		}
	}

	public static void setCol(int idx) {
		if (idx == -1) {
			return;
		}

		Node curr = tree[idx];

		if (curr.left != -1) {
			tree[curr.left].col = tree[idx].col - tree[curr.left].rightCnt - 1;

			setCol(curr.left);
		}
		if (curr.right != -1) {
			tree[curr.right].col = tree[idx].col + tree[curr.right].leftCnt + 1;

			setCol(curr.right);
		}
	}

	public static int getCnt(int idx) {
		if (idx == -1) {
			return 0;
		}

		Node curr = tree[idx];

		int sum = 1;

		curr.leftCnt = getCnt(curr.left);
		curr.rightCnt = getCnt(curr.right);

		sum = sum + curr.leftCnt + curr.rightCnt;
		return sum;
	}

	public static int getParent(int child) {
		if (child == parents[child]) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static void union(int parent, int child) {
		if (child == -1) {
			return;
		}
		int pp = getParent(parent);
		int pc = getParent(child);

		parents[pc] = pp;
	}
}

class Node {
	int left;
	int right;
	int leftCnt;
	int rightCnt;
	int col;

	@Override
	public String toString() {
		return "Node [left=" + left + ", right=" + right + ", leftCnt=" + leftCnt + ", rightCnt=" + rightCnt + ", col="
				+ col + "]";
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}