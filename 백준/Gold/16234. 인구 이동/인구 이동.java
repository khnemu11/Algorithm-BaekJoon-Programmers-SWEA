import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 목표)
 * 인구 이동이 일어나는 총 횟수
 * 
 * 
 * N : 맵의 크기
 * L : 인구 차이의 최솟값
 * R : 인구 차이의 최대값
 * 
 * 인구 이동 : 현재 도시의 인구가 인접한 4방향의 도시의 인구의 차이 diff가 L<= diff <=R을 만족하는 경우 해당 방향의 국경선을 연다(노드가 연결된다.)
 * 인구 이동을 끝나면 해당 도시의 모든 인구더하고 를 도시의 개수만큼 나눈다.
   
  구현 방법) 
   유니온-파인드를 이용해 인구이동 조건을 만족하는 도시들의 부모를 같게한다.
  부모가 같은 곳만 방문하여 (dfs)인구의 합을 구한 후 연합한 도시의 개수로 나누어 분배한다.
  인구 이동이 더이상 없을 때 까지 반복하고 횟수를 출력
 */

public class Main {
	static int parents[];
	static int map[][];
	static int populations[];
	static int childNum[];
	static int L;
	static int R;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		L = Integer.valueOf(st.nextToken());
		R = Integer.valueOf(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		int time = 0;
//		printArr();
		while (true) {
			parents = new int[N * N];
			populations = new int[N * N];
			childNum = new int[N * N];

			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}

			if (!hasPopulationMove()) {
				break;
			}
//			System.out.println(Arrays.toString(parents));
//			System.out.println(Arrays.toString(populations));
//			System.out.println(Arrays.toString(childNum));
//			System.out.println();

			movePopulation();
//			printArr();
			time++;
		}

		bw.write(time + "\n");
		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void printArr() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void movePopulation() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int parent = getParent(i * map.length + j);
				populations[parent] += map[i][j];
				childNum[parent]++;
			}
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int parent = getParent(i * map.length + j);
				map[i][j] = populations[parent] / childNum[parent];
			}
		}
	}

	public static boolean hasPopulationMove() {
		boolean isValid = false;
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int currCity = (map.length * i + j);
				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(i + upDown[k], j + leftRight[k]);
					int nextCity = (map.length * next.row + next.col);

					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
						continue;
					}
					if (getParent(nextCity) == getParent(currCity)) {
						continue;
					}

					int diff = Math.abs(map[next.row][next.col] - map[i][j]);

					if (L > diff || diff > R) {
						continue;
					}
					isValid = true;

					union(nextCity, currCity);
				}
			}
		}

		return isValid;
	}

	public static int getParent(int child) {
		if (parents[child] == child) {
			return parents[child];
		}
		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static void union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa < pb) {
			parents[pb] = pa;
		} else {
			parents[pa] = pb;
		}
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