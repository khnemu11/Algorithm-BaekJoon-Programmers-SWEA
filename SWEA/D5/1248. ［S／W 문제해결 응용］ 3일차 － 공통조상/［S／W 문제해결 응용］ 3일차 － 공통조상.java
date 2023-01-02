import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int V = row[0];
			int E = row[1];
			int aVal = row[2];
			int bVal = row[3];
			int parentArr[] = new int[V + 1];
			Node tree[] = new Node[V + 1]; // 왼쪽, 오른쪽 자식으로 바로 탐색이 가능하게 링크드 리스트가 아니라 배열로 표현
			boolean visited[] = new boolean[V + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < tree.length; i++) {
				tree[i] = new Node(i);
			}

			parentArr[1] = 1;
			// 트리에 자식을 넣고 각 노드 별 부모 배열 추가
			for (int i = 0; i < E; i++) {
				int parent = Integer.valueOf(st.nextToken());
				int child = Integer.valueOf(st.nextToken());

				parentArr[child] = parent;
				tree[parent].add(new Node(child));
			}

			// 목표 노드의 부모 노드를 체크
			int curr = aVal;
			visited[curr] = true;

			while (parentArr[curr] != curr) {
				visited[curr] = true;
				curr = parentArr[curr];
			}

			curr = bVal;

			while (!visited[curr] && parentArr[curr] != curr) {
				curr = parentArr[curr];
			}

			int sameParent = curr;

			// 자식 노드 개수 세기
			int cnt = 0;
			Queue<Node> q = new LinkedList<>();
			q.add(tree[sameParent]);

			while (!q.isEmpty()) {
				cnt++;
				Node currNode = q.poll();

				if (currNode.left != null) {
					q.add(tree[currNode.left.val]);
				}
				if (currNode.right != null) {
					q.add(tree[currNode.right.val]);
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " " + sameParent + " " + cnt);
			bw.write(sb.toString());
			bw.newLine();
		}

		bw.flush();
	}
}

class Node {
	int val;
	Node left;
	Node right;

	public Node() {
	}

	public Node(int val) {
		this.val = val;
	}

	public void add(Node curr) {
		if (left == null) {
			this.left = curr;
		} else {
			this.right = curr;
		}
	}
}
