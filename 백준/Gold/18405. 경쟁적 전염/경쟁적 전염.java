import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * S초 만큼 바이러스가 4방향으로 퍼진다 
 * ->BFS
 * 
 * 바이러스는 번호가 작은 순서로 퍼지고 이미 해당 위치에 바이러스가 있다면 더이상 퍼지지 않는다
 * -> 다중 큐로 구현
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());

		Queue<Coordinate>[] virusList = new LinkedList[K + 1];

		for (int i = 0; i <= K; i++) {
			virusList[i] = new LinkedList<>();
		}

		int map[][] = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());

				virusList[map[i][j]].add(new Coordinate(i, j));
			}
		}

		st = new StringTokenizer(br.readLine());
		int S = Integer.valueOf(st.nextToken());

		Coordinate target = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		//바이러스가 퍼지는 부분
		for (int time = 1; time <= S; time++) {
			for (int type = 1; type <= K; type++) {
				int loop = virusList[type].size();

				while (loop-- > 0) {
					Coordinate curr = virusList[type].poll();

					for (int k = 0; k < upDown.length; k++) {
						Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

						if (next.row <= 0 || next.col <= 0 || next.row >= map.length || next.col >= map[0].length) {
							continue;
						}
						if (map[next.row][next.col] != 0) {
							continue;
						}

						map[next.row][next.col] = type;
						virusList[type].add(next);
					}
				}
			}
		}
		
		System.out.println(map[target.row][target.col]);
		
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