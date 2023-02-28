import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	연락한 사람 중 가장 큰 수 반환
	->우선순위 큐를 이용해 깊이 > 숫자 크기를 우선순위로 큰 값 반환
*/
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int E = Integer.valueOf(st.nextToken());
			int start = Integer.valueOf(st.nextToken());

			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

			for (int i = 0; i <= 100; i++) {
				graph.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());

				graph.get(from).add(to);
			}
			boolean visited[] = new boolean[101];

			PriorityQueue<Node> pq = new PriorityQueue<>();
			Queue<Integer> q = new LinkedList<>();
			q.add(start);
			int depth = 0;

			while (!q.isEmpty()) {
				int loop = q.size();

				while (loop-- > 0) {
					int curr = q.poll();
					if (visited[curr]) {
						continue;
					}
					visited[curr] = true;
					pq.add(new Node(depth, curr));
//					System.out.print(curr + " ");
					for (int to : graph.get(curr)) {
						if (visited[to]) {
							continue;
						}

						visited[curr] = true;
						q.add(to);
					}
				}
//				System.out.println();
				depth++;
			}

			System.out.println("#" + testcase + " " + pq.poll().val);
		}
	}
}

class Node implements Comparable<Node> {
	int depth;
	int val;

	public Node(int depth, int val) {
		this.depth = depth;
		this.val = val;
	}

	@Override
	public int compareTo(Node o) {
		if (this.depth == o.depth) {
			return o.val - this.val;
		}

		return o.depth - this.depth;
	}
}