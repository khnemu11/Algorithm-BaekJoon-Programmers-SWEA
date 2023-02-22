import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int map[][][];
	static int move[][];
	static int upDown[] = { 0, -1, 0, 1, 0 };
	static int leftRight[] = { 0, 0, 1, 0, -1 };
	static int MAP_SIZE = 10;
	static int PEOPLE_NUM = 2;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			init();
			int max = charge();
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(testcase).append(" ").append(max).append("\n");
			bw.write(sb.toString());
		}

		bw.flush();
		bw.close();
	}

	public static int charge() {
		int max = 0;

		Coordinate A = new Coordinate(0, 0);
		Coordinate B = new Coordinate(map[0].length - 1, map[0][0].length - 1);

		for (int i = 0; i < move[0].length; i++) {
			A = new Coordinate(A.row + upDown[move[0][i]], A.col + leftRight[move[0][i]]);
			B = new Coordinate(B.row + upDown[move[1][i]], B.col + leftRight[move[1][i]]);
			int sum = 0;

			for (int bcA = 0; bcA < map.length; bcA++) {
				for (int bcB = 0; bcB < map.length; bcB++) {
					if (bcA == bcB && map[bcA][A.row][A.col] > 0 && map[bcB][B.row][B.col] > 0) {
						sum = Math.max(sum, (map[bcA][A.row][A.col] + map[bcB][B.row][B.col]) / 2);
					} else {
						sum = Math.max(sum, map[bcA][A.row][A.col] + map[bcB][B.row][B.col]);
					}
				}
			}
			max += sum;
		}

		return max;
	}

	public static void init() throws IOException { // 최소값, 집, 회사, 고객 좌표 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.valueOf(st.nextToken()); // 사람 이동 횟수
		int A = Integer.valueOf(st.nextToken()); // 배터리 충전소 개수
		map = new int[A][MAP_SIZE][MAP_SIZE];
		move = new int[PEOPLE_NUM][M + 1];

		move[0][0] = 0; // 처음 위치도 충전값을 저장해야 하므로 안움직이는 변수 추가
		move[1][0] = 0;

		for (int n = 0; n < move.length; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < move[0].length; i++) {
				move[n][i] = Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			BatteryCharger bc = new BatteryCharger(Integer.valueOf(st.nextToken()) - 1,
					Integer.valueOf(st.nextToken()) - 1, Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken())); // 좌표는 0,0부터 시작이므로 좌표 부분은 1을 뺀다
			setBattery(i, bc);
		}
	}

	public static void setBattery(int idx, BatteryCharger bc) {
		int range = 0;
		boolean visited[][] = new boolean[map[0].length][map[0][0].length];

		Queue<Coordinate> q = new LinkedList<>();
		q.add(bc);

		while (range <= bc.coverage) {
			int loop = q.size();

			while (loop-- > 0) {
				Coordinate curr = q.poll();

				map[idx][curr.row][curr.col] = bc.performance;

				for (int k = 1; k < upDown.length; k++) {
					Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
					if (next.row < 0 || next.col < 0 || next.row >= map[0].length || next.col >= map[0][0].length
							|| visited[next.row][next.col]) {
						continue;
					}
					visited[next.row][next.col] = true;
					q.add(next);
				}

			}

			range++;
		}
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class BatteryCharger extends Coordinate {
	int coverage;
	int performance;

	public BatteryCharger(int col, int row, int coverage, int performance) {
		super(row, col);
		this.coverage = coverage;
		this.performance = performance;
	}
}