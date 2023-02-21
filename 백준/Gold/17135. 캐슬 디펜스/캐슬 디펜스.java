import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int map[][];
	static int max_score;
	static Coordinate archers[];
	static int upDown[] = { 0, -1, 0 };
	static int leftRight[] = { -1, 0, 1 };
	static int MAX_ARCHER_NUM = 3;
	static int D, N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		D = Integer.valueOf(st.nextToken());

		map = new int[N + 1][M];
		archers = new Coordinate[MAX_ARCHER_NUM];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		selectArcher(0, 0);
		System.out.println(max_score);
		br.close();
	}

	public static void selectArcher(int idx, int count) {
		if (count == MAX_ARCHER_NUM) {
//			System.out.println(Arrays.toString(archers));
			gameStart(N, D);
		} else {
			for (int i = idx; i < map[0].length; i++) {
				archers[count] = new Coordinate(map.length - 1, i);
				selectArcher(i + 1, count + 1);
			}
		}
	}

	public static void gameStart(int N, int D) {
		int score = 0;
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}

		for (int round = 1; round <= N; round++) {
			score += shoot(D, temp);
			move(N, temp);
//			printArr(temp);
		}

//		System.out.println("score : " + score);
		max_score = Math.max(score, max_score);
	}

	public static int shoot(int D, int temp[][]) {
		HashSet<Coordinate> targets = new HashSet<>();
		for (Coordinate archer : archers) {
			Coordinate target = aim(temp, new Coordinate(archer.row - 1, archer.col), D);

			if (target != null) {
				targets.add(target);
			}
		}
//		System.out.println("target : " + targets);
		for (Coordinate target : targets) {
			temp[target.row][target.col] = 0;
		}

		return targets.size();
	}

	public static Coordinate aim(int temp[][], Coordinate target, int D) {
		Queue<Coordinate> q = new LinkedList<>();
		int range = 1;
		q.add(target);
		boolean visited[][] = new boolean[temp.length][temp[0].length];
		Coordinate enemy = null;

		while (!q.isEmpty()) {
			int loop = q.size();

			while (loop-- > 0) {
				Coordinate curr = q.poll();
				if (temp[curr.row][curr.col] == 1) {
					if (enemy == null || enemy.col > curr.col) {
						enemy = curr;
					}
				} else {
					visited[curr.row][curr.col] = true;
					for (int k = 0; k < upDown.length; k++) {
						Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

						if (next.row < 0 || next.col < 0 || next.row >= temp.length || next.col >= temp[0].length
								|| visited[next.row][next.col]) {
							continue;
						}
						visited[next.row][next.col] = true;
						q.add(next);
					}
				}
			}
			range++;

			if (enemy != null || range > D) {
				break;
			}
		}

		return enemy;
	}

	public static void move(int N, int temp[][]) {
		for (int row = N - 1; row >= 1; row--) {
			for (int col = 0; col < temp[0].length; col++) {
				temp[row][col] = temp[row - 1][col];
			}
		}
		for (int col = 0; col < temp[0].length; col++) {
			temp[0][col] = 0;
		}
	}

	public static void printArr(int map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Coordinate {
	int row;
	int col;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	@Override
	public boolean equals(Object o) {
		Coordinate target = (Coordinate) o;

		if (target.row != this.row) {
			return false;
		}
		if (target.col != this.col) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return row + col;
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}