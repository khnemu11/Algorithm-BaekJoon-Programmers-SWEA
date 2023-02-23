import java.io.*;
import java.util.*;
/*
	풀이 알고리즘
	궁수를 N개 중에서 3명을 선택 -> 최대 경우의 수 : 15C3
	적이 사거리 이내에 있다면 가장 가까움 > 가장 왼쪽의 우선순위에 따라 적을 중복하여 제거
	모든 적이 없어질 때 까지 라운드 마다 적을 아래로 한칸 이동
	가장 적을 많이 없앤 수 출력
*/

class Solution {
	static int maxKill = 0; // 최대한 죽인 적의 수
	static int upDown[] = { 0, -1, 0 }; // 우선순위에 맞게 왼,위,오 이동
	static int leftRight[] = { -1, 0, 1 };
	static int originalMap[][]; // 원본 맵
	static int maxRange; // 최대 사거리
	static int ARCHER_NUM = 3; // 선택할 궁수의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 높이
		int M = Integer.valueOf(st.nextToken()); // 너비
		maxRange = Integer.valueOf(st.nextToken()); //최대 사거리

		originalMap = new int[N + 1][M];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				originalMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		selectArchers(0, 0, new Coordinate[ARCHER_NUM]);

		System.out.println(maxKill);
		br.close();
	}

	public static void selectArchers(int idx, int startArcherIdx, Coordinate archers[]) { // 궁수를 3명 선택하는 메소드 (현재 인덱스, 다음
																							// 궁수를 선택할 시작 인덱스, 현재까지 선택한
																							// 궁수들)
		if (idx == archers.length) {
			gameStart(archers); // 궁수를 모두 선택했으면 게임시작
		} else {
			for (int i = startArcherIdx; i < originalMap[0].length; i++) {
				archers[idx] = new Coordinate(originalMap.length - 1, i);
				selectArchers(idx + 1, i + 1, archers);
			}
		}
	}

	public static void gameStart(Coordinate archers[]) { // 조건대로 캐슬 디펜스를 시작하는 메소드 (선택한 궁수)
		int roundMap[][] = copyArr(originalMap); // 초기 맵을 복사
		int enemyCnt = countEnemy(roundMap); // 현재 맵의 적의 수
		int killCnt = 0; // 죽인 적의 수

		while (enemyCnt > 0) {
			HashSet<Coordinate> enemySet = findEnemy(maxRange, roundMap, archers); // 죽여야 할 적의 set
			killCnt = killCnt + kill(enemySet, roundMap); // 죽인 적의 수 갱신
			moveEnemy(roundMap); // 적 이동
			enemyCnt = countEnemy(roundMap); // 남은 적의 개수
		}

		maxKill = Math.max(maxKill, killCnt); // 죽인 적의 수 최대값으로 최신화
	}

	public static void moveEnemy(int map[][]) { // 적을 이동하는 메소드 (적의 위치가 담긴 2차원 배열)
		for (int row = map.length - 3; row >= 0; row--) { // 맨 아래는 궁수 위치, 그 위는 없어질 적들이므로 아래에서 2번째 부터 적을 내린다.
			for (int col = 0; col < map[0].length; col++) {
				map[row + 1][col] = map[row][col];
			}
		}

		for (int col = 0; col < map[0].length; col++) { // 맨 위의 적들이 아래로 내려갔으므로 없애기
			map[0][col] = 0;
		}
	}

	public static int kill(HashSet<Coordinate> enemySet, int map[][]) { // 적을 죽이는 메소드 (적의 좌표 set, 적의 위치 2차원 배열)
		int killCnt = 0;

		for (Coordinate enemey : enemySet) { // 적의 위치를 0으로 변경
			map[enemey.row][enemey.col] = 0;
			killCnt++;
		}

		return killCnt;
	}

	public static HashSet<Coordinate> findEnemy(int maxRange, int map[][], Coordinate archers[]) { // 적을 우선순위에 따라 bfs로
																									// 탐색하는 메소드 (최대 사거리,
																									// 위치 배열, 선택한 궁수 좌표)
		HashSet<Coordinate> enemySet = new HashSet<>();

		for (Coordinate archer : archers) {
			int range = 1; // 시작 사거리
			boolean isFind = false; // 적을 찾았다는 플래그
			boolean visited[][] = new boolean[map.length][map[0].length];
			Coordinate target = new Coordinate(archer.row - 1, archer.col);
			Queue<Coordinate> targetQ = new LinkedList<>(); // 사격할 수 있는 좌표
			targetQ.add(target);

			while (range <= maxRange && !isFind) { // 사거리를 넘치거나 적을 찾으면 탈출
				int loop = targetQ.size();

				while (loop-- > 0) {
					target = targetQ.poll();
					visited[target.row][target.col] = true;

					if (map[target.row][target.col] == 1) { // 우선순위에 따라 적발견
						isFind = true;
						enemySet.add(target);
						break;
					}

					for (int k = 0; k < upDown.length; k++) {
						Coordinate next = new Coordinate(target.row + upDown[k], target.col + leftRight[k]); // 왼/위/오른쪽
																												// 순서로 적
																												// 탐색

						if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length
								|| visited[next.row][next.col]) { // 이미 탐색했거나 배열을 넘어서면 다음으로
							continue;
						}

						targetQ.add(next); // 적 저장
					}
				}
				range++; // 다음 사거리로 이동
			}
		}

		return enemySet;
	}

	public static int[][] copyArr(int map[][]) { // 2차원배열을 복사하는 메소드 (복사할 2차원 배열)
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}

		return temp;
	}

	public static int countEnemy(int map[][]) {// 적의 개수를 셀 메소드 (2차원 배열)
		int cnt = 0;

		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		return cnt;
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
	public boolean equals(Object o) {// hashset을 위한 비교 메소드 재정의
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
	public int hashCode() { // hashset을 위한 해시코드 재정의
		return row * 31 + col * 17;
	}
}
