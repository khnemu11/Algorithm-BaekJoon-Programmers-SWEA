import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
	풀이 과정
	모든 노드가 이어진 거리의 합의 최소 -> mst
	모든 노드가 모든 노드로 이동할 수 있으므로 그래프의 크기는 N*N(최대 1,000*1,000) -> 배열 및 큐생성가능
	모든 노드간의 거리를 구하고 거리 값이 작은 순서대로 정렬->우선 순위 큐 사용
	이때 이미 연결된 노드의 가중치 = 0
	크루스칼 알고리즘을 사용하여 짧은 노드간의 거리만 채택, 사이클이 발생하는지는 유니온-파인드로 해결
	채택하면서 거리의 합 추가
	
*/

public class Main {
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		Coordinate spaceShips[] = new Coordinate[N + 1];
		parents = new int[N + 1];
		double graph[][] = new double[N + 1][N + 1];

		for (int i = 1; i <= N; i++) { // 좌표 저장
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());

			spaceShips[i] = new Coordinate(x, y);
			parents[i] = i;
		}

		for (int from = 1; from < spaceShips.length; from++) { // 좌표별 거리 저장
			for (int to = from; to < spaceShips.length; to++) {
				graph[from][to] = getDistance(spaceShips[from], spaceShips[to]);
				graph[to][from] = getDistance(spaceShips[from], spaceShips[to]);
			}
		}

		PriorityQueue<Path> pq = new PriorityQueue<>(); // 간선간의 거리를 낮은 순으로 저장할 큐
		double sum = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());

			graph[to][from] = 0;
			graph[from][to] = 0; // 이미 연결된 노드의 거리를 0으로 초기화
		}

		for (int from = 1; from < spaceShips.length; from++) {
			for (int to = from + 1; to < spaceShips.length; to++) {
				pq.add(new Path(from, to, graph[from][to])); // 모든 노드간의 거리 저장
			}
		}
		while (!pq.isEmpty()) {
			Path path = pq.poll();

			if (!union(path.from, path.to)) { // 사이클이 발생한다면 다음으로
				continue;
			}
			sum += path.distance;
		}

		System.out.println(String.format("%.2f", sum));
	}

	public static double getDistance(Coordinate a, Coordinate b) { // 두 좌표간의 거리를 구하는 매소드
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	public static int getParent(int child) { // 부모를 찾는 매소드
		if (parents[child] == child) {
			return child;
		} else {
			parents[child] = getParent(parents[child]);
			return parents[child];
		}
	}

	public static boolean union(int a, int b) { // 사이클 판별 및 노드의 집합끼리 합치는 매소드
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa == pb) {
			return false;
		}

		parents[pa] = pb;

		return true;
	}
}

class Path implements Comparable<Path> {
	int from;
	int to;
	double distance;

	public Path(int from, int to, double distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	@Override
	public int compareTo(Path o) {
		if (this.distance < o.distance) {
			return -1;
		}
		return 1;
	}
}

class Coordinate {
	int x;
	int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}