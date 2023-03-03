import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * 풀이 알고리즘
 * 
 * 	0) 문제 목표
 * 	  시작점부터 끝점까지의 총 복구시간이 짧은 것
 *    -> 최단거리, 다익스트라
 *    E = N^2 = 100,000 <-nlogn가능
 *    
 *    
*/
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.valueOf(br.readLine());

			int map[][] = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				int input[] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = input[j];
				}
			}
			Coordinate start = new Coordinate(0, 0);
			Coordinate end = new Coordinate(N - 1, N - 1);

			PriorityQueue<Road> pq = new PriorityQueue<>();
			int distance[][] = new int[N + 1][N + 1];
			int INF = 200_000;
			for (int i = 0; i <= N; i++) {
				Arrays.fill(distance[i], INF);
			}

			distance[start.row][start.col] = 0;
			pq.add(new Road(start.row, start.col, distance[start.row][start.col]));

			while (!pq.isEmpty()) {
				Road mid = pq.poll();

				if (distance[mid.row][mid.col] < mid.depth) {
					continue;
				}

				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(mid.row + upDown[k], mid.col + leftRight[k]);

					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
						continue;
					}

					if (distance[next.row][next.col] > distance[mid.row][mid.col] + map[next.row][next.col]) {
						distance[next.row][next.col] = distance[mid.row][mid.col] + map[next.row][next.col];
						pq.add(new Road(next.row, next.col, distance[next.row][next.col]));
					}
				}
			}
			bw.write("#" + testcase + " " + distance[end.row][end.col] + "\n");
		}

		bw.flush(); // 결과 출력
		br.close();
		bw.close();
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

class Road extends Coordinate implements Comparable<Road> {
	int depth;

	public Road(int row, int col, int depth) {
		super(row, col);
		this.depth = depth;
	}

	@Override
	public int compareTo(Road o) {
		return this.depth - o.depth;
	}
}