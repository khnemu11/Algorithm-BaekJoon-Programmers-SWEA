import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 풀이 과정
 * 
 * 목표)
 * CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리이다.
 * 이때 최소 CC(i)를 구하라
 * 
 * 풀이 과정)
 * CC(i)는 즉 한 노드에서 모든 노드간의 최단거리의 합이다.
 * 한 노드에서 모든 노드간의 최단거리 => 플로이드 와샬
 * 
 * 노드의 개수 : 1000개
 * 플로이드 와샬을 활용한 시간 복잡도 =1000*1000*1000 = 10억 > 1억 시간초과(2초)
 * 
 * 연속된 다익스트라를 활용한 시간 복잡도 = 1000*1000*log(1000) = <1억 시간내 가능
 * 
 * 한줄에 많은 값이 들어가는 테스트 케이스
 * =>Scanner를 활용해 숫자 단위로 읽자
 * 
 * */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = sc.nextInt(); // 테스트 케이스 개수
		int INF = 2_000_000;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();

			ArrayList<Node> graph[] = new ArrayList[N];

			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}



			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int val = sc.nextInt();
					if (val == 1) {
						graph[i].add(new Node(j, val));
					}
				}
			}
			int minCC = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				int distance[] = new int[N];
				boolean visited[] = new boolean[N];

				Arrays.fill(distance, INF);
				distance[i] = 0;

				PriorityQueue<Node> pq = new PriorityQueue<>();
				pq.add(new Node(i, 0));

				while (!pq.isEmpty()) {
					Node curr = pq.poll();

					if (visited[curr.val]) {
						continue;
					}

					visited[curr.val] = true;

					for (Node to : graph[curr.val]) {
						if (distance[to.val] > distance[curr.val] + to.cost) {
							distance[to.val] = distance[curr.val] + to.cost;
							pq.add(new Node(to.val, distance[to.val]));
						}
					}
				}
				int sum = Arrays.stream(distance).sum();

				minCC = minCC < sum ? minCC : sum;
			}

			bw.write("#" + test_case + " " + minCC + "\n");
		}
		bw.flush();
	}
}

class Node implements Comparable<Node> {
	int val;
	int cost;

	public Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", cost=" + cost + "]";
	}

}