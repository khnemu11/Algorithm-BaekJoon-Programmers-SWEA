import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	지름의 길이 = 해당 노드의 왼쪽 자식의 길이의 최대 길이 + 오른쪽 자식의 길이의 최대 길이
	해당 노드의 오른쪽+왼쪽 노드의 길이의 합이 최대 지름이 될 수 있으므로 최대값 판단
	부모 노드는 자식 노드 중 가장 긴 노드만 필요하므로 자식의 왼쪽, 오른쪽 노드 중 긴 것만 리턴 -> 재귀
	
	루트가 주어지지 않으므로 루트 노드 찾을 필요 있음 -> 유니온 파인드로 루트 노드 찾기
*/

public class Main {
	static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
	static int max;
	static boolean visited[];
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		visited = new boolean[N + 1];
		parents = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
			parents[i] = i;
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.valueOf(st.nextToken());
			while (st.hasMoreTokens()) {
				int child = Integer.valueOf(st.nextToken());
				if (child == -1) {
					break;
				}
				int weight = Integer.valueOf(st.nextToken());

				tree.get(parent).add(new Node(child, weight));

				union(parent, child);
			}
		}

		getLength(new Node(parents[1], 0));
		bw.write(max + "\n");
		bw.flush();
	}

	public static int getParent(int child) {
		if (child == parents[child]) {
			return child;
		} else {
			parents[child] = getParent(parents[child]);
			return parents[child];
		}
	}

	public static void union(int parent, int child) {
		int pp = getParent(parent);
		int pc = getParent(child);

		parents[pc] = pp;
	}

	public static int getLength(Node curr) { // 자식의 노드만 사용해서 지름의 길이를 구하고 긴 노드를 리턴 (Node curr : 현재 노드)
		PriorityQueue<Integer> childLength = new PriorityQueue<>(Collections.reverseOrder());
		childLength.add(0); // 자식이 없는 경우 대비
		childLength.add(0); // 자식이 없는 경우 대비

		visited[curr.val] = true;

		for (Node child : tree.get(curr.val)) {
			if (visited[child.val]) {
				continue;
			}
			visited[child.val] = true;
			int length = getLength(child) + child.weight;
			childLength.add(length);
		}
		int longest = childLength.poll();

		max = Math.max(max, longest + childLength.poll());

		return longest;
	}
}

class Node {
	int val;
	int weight;

	public Node(int val, int weight) {
		this.val = val;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", weight=" + weight + "]";
	}

}
