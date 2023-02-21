import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 궁수는 최대 3명까지
 * 적을 죽이는 우선순위는 거리 > 왼쪽
 * 최대 N은 15개이므로 궁수를 고르는 최대 개수는 15C3 = 455
 * 궁수당 최대 사거리 이동 = 3+5+7+...+21 = 120
 * 최대 적 이동회수 = 15
 * 시간 복잡도 = 15*120*455 = 819,000
 * */

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

	public static void selectArcher(int idx, int count) { // 궁수 고르기 (궁수 인덱스, 개수)
		if (count == MAX_ARCHER_NUM) {
			gameStart(N, D); // 궁수를 모두 골랐으면 적 죽이기 시작
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

		for (int i = 0; i < map.length; i++) { // 기존 맵을 복사
			for (int j = 0; j < map[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}

		for (int round = 1; round <= N; round++) { // 맨 위의 행이 맨 아래를 벗어날 때 까지 루프
			score += shoot(D, temp); // 적을 죽이기
			move(N, temp); // 적을 내리기
		}

		max_score = Math.max(score, max_score); // 최대 점수(적을 죽인 횟수) 갱신
	}

	public static int shoot(int D, int temp[][]) {
		HashSet<Coordinate> targets = new HashSet<>(); // 중복 적을 제거기 위한 set
		for (Coordinate archer : archers) {
			Coordinate target = aim(temp, new Coordinate(archer.row - 1, archer.col), D); // 적 발견

			if (target != null) { // 적을 죽일 수 있다면 타겟에 추가
				targets.add(target);
			}
		}
		for (Coordinate target : targets) { // 타게팅된 적을 없애기
			temp[target.row][target.col] = 0;
		}

		return targets.size(); // 죽일 적 개수
	}

	public static Coordinate aim(int temp[][], Coordinate target, int D) { // 가장 가까우면서 왼쪽에 있는 적을 찾는 메소드 (맵, 적 좌표, 사거리)
		Queue<Coordinate> q = new LinkedList<>();
		int range = 1; // 초기 사거리
		q.add(target);
		boolean visited[][] = new boolean[temp.length][temp[0].length];
		Coordinate enemy = null; // 발견할 적

		while (!q.isEmpty()) {
			int loop = q.size(); // bfs를 위한 크기 저장

			while (loop-- > 0) {
				Coordinate curr = q.poll();
				if (temp[curr.row][curr.col] == 1) { // 적이 맞고 기존 적보다 왼쪽에 있다면 변경
					if (enemy == null || enemy.col > curr.col) {
						enemy = curr;
					}
				} else { // 적이 없으면 다음 방향 이동, 이때 아래로 갈일은 없으므로 위,왼,오만 확인
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

			if (enemy != null || range > D) { // 적을 사거리 내로 발견했거나(짧은 순서대로) 사거리가 벗어나면 탈출
				break;
			}
		}

		return enemy;
	}

	public static void move(int N, int temp[][]) { // 모든 적을 아래로 내리를 메소드
		for (int row = N - 1; row >= 1; row--) {
			for (int col = 0; col < temp[0].length; col++) {
				temp[row][col] = temp[row - 1][col];
			}
		}
		for (int col = 0; col < temp[0].length; col++) {
			temp[0][col] = 0;
		}
	}
}

class Coordinate {
	int row;
	int col;

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
