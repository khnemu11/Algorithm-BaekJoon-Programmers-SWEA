import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	경찰차와 목표지점 까지의 거리는 절대값(경찰차 행 - 목표지점 행) +절대값(경찰차 열 - 목표지점 열)
	
*/

public class Main {
	static int dp[][];
	static Coordinate events[];
	static int W, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.valueOf(br.readLine());
		W = Integer.valueOf(br.readLine());
		dp = new int[W + 1][W + 1]; // 경찰1이 i, 경찰2가 j일때 W까지의 최소 거리
		events = new Coordinate[W + 1];
		for (int i = 1; i <= W; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			events[i] = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}

		int min = getMinDistance(1, 0, 0);
		int oneIdx = 0;
		int twoIdx = 0;

		Coordinate police1 = new Coordinate(1, 1);

		StringBuilder result = new StringBuilder(min + "\n");

		for (int i = 1; i <= W; i++) {
			int policeOneDistance = dp[i][twoIdx] + getDistance(police1, events[i]);

			if (policeOneDistance == dp[oneIdx][twoIdx]) {
				result.append(1 + "\n");
				police1 = events[i];
				oneIdx = i;
			} else {
				result.append(2 + "\n");
				twoIdx = i;
			}
		}
		bw.write(result.toString());

		bw.flush();
	}

	public static int getMinDistance(int curr, int police1, int police2) {
		if (curr > W) {
			return 0;
		} else if (dp[police1][police2] != 0) {
			return dp[police1][police2];
		} else {
			Coordinate police1Coord = police1 == 0 ? new Coordinate(1, 1) : events[police1]; // 현재 경찰1위치
			Coordinate police2Coord = police2 == 0 ? new Coordinate(N, N) : events[police2]; // 현재 경찰 2
			dp[police1][police2] = Math.min(
					getMinDistance(curr + 1, curr, police2) + getDistance(police1Coord, events[curr]),
					getMinDistance(curr + 1, police1, curr) + getDistance(police2Coord, events[curr]));
			return dp[police1][police2];
		}
	}

	public static int getDistance(Coordinate from, Coordinate to) {
		return Math.abs(from.row - to.row) + Math.abs(from.col - to.col);
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
	public String toString() {
		return "[" + row + ", " + col + "]";
	}

}
