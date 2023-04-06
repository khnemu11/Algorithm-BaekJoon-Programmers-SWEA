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

		// 부모 노드 설정
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		// 트리 생성
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

		// 루트 노드 찾기
		int root = getParent(1);

		getCnt(root); // 각 노드별 왼쪽/오른쪽 자식 개수 구하기
		setCol(root); // 각 노드별 좌표 구하기
		getMaxWidth(root); // 깊이별 너비 구하기

		System.out.println(minDepth + " " + maxWidth);
	}

	// 깊이별 최대 너비를 구하는 메소드
	private static void getMaxWidth(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		int depth = 1; // 높이
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

				maxCol = Math.max(maxCol, col);// 가장 오른쪽에 있는 좌표
				minCol = Math.min(minCol, col); // 가장 왼쪽에 있는 좌표

				q.add(tree[curr].left);
				q.add(tree[curr].right);
			}

			int width = maxCol - minCol + 1;

			if (maxWidth < width) { // 최대값 설정
				maxWidth = width;
				minDepth = depth; // 이때 가장 높이가 낮은 것 부터 확인하므로 최대 너비가 같은 경우 먼저 나온 깊이가 정답
			}

			depth++;
		}
	}

	// 노드 별 열좌표를 구하는 메소드
	public static void setCol(int idx) {
		if (idx == -1) {
			return;
		}

		Node curr = tree[idx];

		if (curr.left != -1) {
			tree[curr.left].col = tree[idx].col - tree[curr.left].rightCnt - 1; // 왼쪽 열 좌표 생성

			setCol(curr.left);
		}
		if (curr.right != -1) {
			tree[curr.right].col = tree[idx].col + tree[curr.right].leftCnt + 1;// 오른쪽 열 좌표 생성

			setCol(curr.right);
		}
	}

	// 각 노드별 자신 포함 왼쪽/오른쪽 자식의 개수를 구하는 메소드
	public static int getCnt(int idx) {
		// 자식이 없으면 패스
		if (idx == -1) {
			return 0;
		}

		Node curr = tree[idx];

		int sum = 1; // 현재 노드 집합의 개수(자기 자신 포함)

		curr.leftCnt = getCnt(curr.left);
		curr.rightCnt = getCnt(curr.right);

		sum = sum + curr.leftCnt + curr.rightCnt;
		return sum;
	}

	// 유니온 파인드를 위한 부모를 구하는 메소드
	public static int getParent(int child) {
		if (child == parents[child]) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	// 유니온 파인드를 위한 노드끼리 병합하는 부분
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
	int left; // 왼쪽 노드
	int right;// 오른쪽 노드
	int leftCnt;// 왼쪽 노드 개수
	int rightCnt;// 왼쪽 노드 개수
	int col;// 노드 행 좌표
}
