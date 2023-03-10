package defalut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘

 	두 행성간의 거리는 두 좌표의 거리가 아닌 x좌표, y좌표 ,z좌표의 차이 중 최소값
 	
 	단수 좌표끼리 모든 거리를 구하면 N^N으로 시간복잡도가 위험함
 	
 	
 	각 좌표의 차이(두 좌표간 경로)별로 최소인 것을 우선적으로 MST생성 -> 각 차이별 정렬 필요
 	같은 경로를 다시가면 안됨 -> 크루스칼을 이용해 경로 방문 확인
 	
 	이를 이용하면 정렬이 3번 일어나므로 3N*logN이기 때문에 시간 내에 가능
 	
 */
public class Main {
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		Coordinate coordinates[] = new Coordinate[N];
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;

			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int z = Integer.valueOf(st.nextToken());

			coordinates[i] = new Coordinate(i, x, y, z);
		}

		PriorityQueue<Path> pQueue = new PriorityQueue<>();

		Arrays.sort(coordinates, (o1, o2) -> o1.x - o2.x); // x정렬

		for (int i = 0; i < coordinates.length - 1; i++) {
			pQueue.add(new Path(coordinates[i], coordinates[i + 1]));
		}

		Arrays.sort(coordinates, (o1, o2) -> o1.y - o2.y);	// y정렬

		for (int i = 0; i < coordinates.length - 1; i++) {
			pQueue.add(new Path(coordinates[i], coordinates[i + 1]));
		}
		Arrays.sort(coordinates, (o1, o2) -> o1.z - o2.z);	// z정렬

		for (int i = 0; i < coordinates.length - 1; i++) {
			pQueue.add(new Path(coordinates[i], coordinates[i + 1]));
		}

		int cost = 0;	//최소 비용 합

		while (!pQueue.isEmpty()) {
			Path minPath = pQueue.poll();

			if (doUnion(minPath)) {	//만약 사이클이 형셩되는 경로가 아니라면 비용 추가
				cost += minPath.cost;	
			}
		}

		bw.write(cost + "\n");
		bw.flush();
	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return parents[child];
		}

		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static boolean doUnion(Path path) {
		int pa = getParent(path.startCoordinate.seq);
		int pb = getParent(path.endCoordinate.seq);

		if (pa == pb) {
			return false;
		}

		else if (pa < pb) {
			parents[pb] = pa;
		} else {
			parents[pa] = pb;
		}

		return true;
	}
}

class Coordinate {
	int seq;
	int x;
	int y;
	int z;

	public Coordinate(int seq, int x, int y, int z) {
		this.seq = seq;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Path implements Comparable<Path> {
	Coordinate startCoordinate;
	Coordinate endCoordinate;
	int cost;

	public Path(Coordinate startCoordinate, Coordinate endCoordinate) {
		this.startCoordinate = startCoordinate;
		this.endCoordinate = endCoordinate;

		this.cost = Math.min(Math.abs(startCoordinate.x - endCoordinate.x),
				Math.abs(startCoordinate.y - endCoordinate.y));
		this.cost = Math.min(this.cost, Math.abs(startCoordinate.z - endCoordinate.z));
	}

	@Override
	public int compareTo(Path o) {
		return this.cost - o.cost;
	}
}
