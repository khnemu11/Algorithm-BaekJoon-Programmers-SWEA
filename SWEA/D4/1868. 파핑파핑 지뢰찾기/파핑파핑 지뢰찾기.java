import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char map[][];
	static int notMineCnt;
	static int upDown[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int leftRight[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= TC; testcase++) {
			init(); // 입력 맵을 숫자 맵으로 초기화
			int clickCnt = click(); // 클릭 횟수 계산

			System.out.println("#" + testcase + " " + clickCnt);
		}

		br.close();
	}

	public static void init() throws NumberFormatException, IOException {	//입력값을 숫자 맵으로 바꾸어주는 초기화 메소드
		int N = Integer.valueOf(br.readLine());
		map = new char[N][N];
		notMineCnt = 0;	//지뢰가 아닌 칸의 개수

		for (int i = 0; i < map.length; i++) { // 맵을 0으로 초기화
			Arrays.fill(map[i], '0');
		}

		for (int i = 0; i < map.length; i++) { // 지뢰 주변 숫자 입력
			char input[] = br.readLine().toCharArray();
			for (int j = 0; j < map[0].length; j++) {
				if (input[j] == '*') {	//지뢰인 경우 주변 8방향 중 지뢰가 아닌 칸의 개수를 1개 증가
					map[i][j] = input[j];
					Coordinate curr = new Coordinate(i, j);
					for (int k = 0; k < upDown.length; k++) {
						Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

						if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
							continue;
						}
						if (map[next.row][next.col] < '0' || map[next.row][next.col] > '9') {
							continue;
						}

						map[next.row][next.col] = (char) (map[next.row][next.col] + 1);
					}
				} else {	//지뢰가 아닌 경우 지뢰가 아닌 칸의 개수 증다
					notMineCnt++;
				}
			}
		}
	}

	public static int click() {	//지뢰가 아닌 칸을 최소횟수로 클릭(0을 우선적으로 클릭)하는 메소드
		boolean visited[][] = new boolean[map.length][map[0].length];
		int clickCnt = 0;	//클릭 횟수
		int visitedCnt = 0; //지뢰가 아닌 칸 방문 횟수

		for (int i = 0; i < map.length; i++) { // 0을 찾아서 클릭
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '0' && !visited[i][j]) {	//0이면서 방문하지 않는 칸을 bfs탐색
					Coordinate start = new Coordinate(i, j);
					Queue<Coordinate> q = new LinkedList<>();
					q.add(start);

					while (!q.isEmpty()) {
						Coordinate curr = q.poll();
						visited[curr.row][curr.col] = true;
						visitedCnt++;

						if (map[curr.row][curr.col] == '0') {	//해당 칸이 0인 경우 주변 8방향 탐색
							for (int k = 0; k < upDown.length; k++) {
								Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

								if (next.row < 0 || next.col < 0 || next.row >= map.length
										|| next.col >= map[0].length) { // 맵 범위를 벗어날 경우
									continue;
								}
								if (map[next.row][next.col] < '0' || map[next.row][next.col] > '9') { // 지뢰인 경우
									continue;
								}
								if (visited[next.row][next.col]) { // 이미 방문한 경우
									continue;
								}
								visited[next.row][next.col] = true;
								q.add(next);
							}
						}
					}

					clickCnt++;	//클릭횟수 증가
				}
			}
		}
		int rest = notMineCnt - visitedCnt; // 아직 방문하지 않은 숫자 구역
		clickCnt = clickCnt + rest; // 방문하지 않는 구역 클릭 추가

		return clickCnt;
	}
}

class Coordinate {
	int row;
	int col;

	Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

}