import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
	노드의 개수는 최대 10만개
	왼쪽, 오른쪽 노드 필요
	한 노드부터 시작하여 부모를 찾고 아직 방문하지 않은 자식이 있다면 자식 탐색
	자식중에 나머지 찾으려는 노드가 있다면 여태까지 방문한 횟수 리턴
	아니라면 부모로 올라감
*/

public class Solution {
	static Node nodes[];
	static int parents[];
	static boolean visited[];
	static int length;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= TC; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken()); // 정점 개수
			int E = Integer.parseInt(st.nextToken()); // 간선 개수
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			nodes = new Node[V + 1];
			parents = new int[V + 1];
			visited = new boolean[V + 1];

			st = new StringTokenizer(br.readLine());
			parents[1] = 1; // 1은 항상 루트이므로
			while (st.hasMoreTokens()) { // 입력받는 부모-자식 노드 생성 및 연결
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				parents[child] = parent;	//자식의 부모 최신화

				if (nodes[parent] == null) {	//자식이나 부모 객체가 없으면 생성
					nodes[parent] = new Node();
				}
				if (nodes[child] == null) {
					nodes[child] = new Node();
				}

				if (nodes[parent].left == 0) {	//왼쪽부터 부모의 자식을 삽입
					nodes[parent].left = child;	
				} else {
					nodes[parent].right = child;
				}
			}

			goToParent(node1);	//노드 1개를 부모까지 탐색
			int commonParent = goToParent(node2); //나머지 노드를 부모까지 탐색하고 이미 탐색한 노드라면 해당 노드가 공통 부모이므로 리턴
			int length = getLength(commonParent); //공통 부모로 길이 계산

			System.out.println("#" + testcase + " " + commonParent + " " + length);
		}

		br.close();
	}

	public static int goToParent(int child) { //부모로 탐색하는 메소드 (자식 노드)
		if (visited[child]) {
			return child;
		} else {
			visited[child] = true;
			return goToParent(parents[child]);
		}
	}

	public static int getLength(int parent) { //노드의 서브트리를 구하는 메소드 (노드 값)
		if (parent == 0) { //노드가 비어있는경우
			return 0;
		} else { //왼쪽, 오른쪽, 자기 자신(1)을 리턴
			return getLength(nodes[parent].left) + getLength(nodes[parent].right) + 1;
		}
	}
}

class Node {
	int left;
	int right;
}