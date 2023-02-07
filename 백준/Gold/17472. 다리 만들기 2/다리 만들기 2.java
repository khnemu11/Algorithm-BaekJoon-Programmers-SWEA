import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	풀이 과정
	각 섬별 군집화 필요 -> dfs로 번호 마킹
	각 섬별 연결하는 다리 필요 -> 1이 있는 위치에서 4방향의 0의 위치에서 해당 방향으로 움직여 섬이 도달하면 그래프에 추가
	해당 다리들로 mst 구하기
	
*/

public class Main {
	static int map[][];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static boolean visited[][];
	static int graph[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		int INF = 100;
		map = new int[height][width];
		visited = new boolean[height][width];

		Queue<Coordinate> q = new LinkedList<>();

		// 입력값 초기화

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());

				if (map[i][j] == 1) {
					q.add(new Coordinate(i, j));
				}
			}
		}

		int no = 1;
		int loop = q.size();

		// 같은 섬끼리 모으기
		while (loop-- > 0) {
			Coordinate curr = q.poll();
			q.add(curr);

			if (visited[curr.row][curr.col]) {
				continue;
			}

			clustering(curr, no);
			no++;
		}
		no--;

		graph = new int[no + 1][no + 1];
		boolean nodeVisited[] = new boolean[no + 1];

		for (int i = 0; i < graph.length; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 섬과 섬사이의 거리를 중 최소값을 그래프로 저장
		while (!q.isEmpty()) {
			Coordinate curr = q.poll();
			findRoute(curr);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		// 프림 알고리즘을 이용해 mst구현
		int cnt = 0;
		int sum = 0;
		while (!pq.isEmpty() && cnt < no) {
			Node curr = pq.poll();

			if (nodeVisited[curr.val]) {
				continue;
			}

			nodeVisited[curr.val] = true;
			cnt++;
			sum += curr.cost;

			for (int to = 1; to < graph[curr.val].length; to++) {
				if (nodeVisited[to] || graph[curr.val][to] == INF) {
					continue;
				}
				pq.add(new Node(to, graph[curr.val][to]));
			}
		}

		if (cnt == no) {
			bw.write(sum + "\n");
		} else {
			bw.write(-1 + "\n");
		}

		bw.flush();
	}

	public static void clustering(Coordinate curr, int no) {
		map[curr.row][curr.col] = no;
		visited[curr.row][curr.col] = true;
		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (isValid(next) && !visited[next.row][next.col] && map[next.row][next.col] > 0) {
				clustering(next, no);
			}
		}
	}

	public static void findRoute(Coordinate curr) {
		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (!isValid(next) || map[next.row][next.col] > 0) {
				continue;
			}

			int cost = 0;

			while (isValid(next)) {
				if (map[next.row][next.col] > 0) {
					break;
				}
				next.row += upDown[k];
				next.col += leftRight[k];
				cost++;
			}
			if (isValid(next) && cost > 1 && map[curr.row][curr.col] != map[next.row][next.col]) {
				graph[map[curr.row][curr.col]][map[next.row][next.col]] = Math
						.min(graph[map[curr.row][curr.col]][map[next.row][next.col]], cost);
				graph[map[next.row][next.col]][map[curr.row][curr.col]] = Math
						.min(graph[map[next.row][next.col]][map[curr.row][curr.col]], cost);
			}
		}
	}

	public static boolean isValid(Coordinate coord) {
		if (coord.row < 0 || coord.row >= map.length || coord.col < 0 || coord.col >= map[0].length) {
			return false;
		}

		return true;
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
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
	public String toString() {
		return "Node [val=" + val + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}