import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	보통 dp 문제 : 현재 위치의 최소값 = 이전 위치의 최소값 + min(이전 위치에서 경찰차 1이 움직인 거리, 이전 위치에서 경찰차 2가 움직인 거리);
	하지만 마지막 경찰차의 2개의 위치가 각각 어디인지 알 수 있으므로 top-bottom 불가
	단 시작 위치는 각각 1,1 N,N으로 정해져 있으므로 bottom-top으로 해결해야함
	현재~마지막 까지의 최소값 = min(현재 위치에서 경찰차 1이 다음 사건까지 움직인 거리 + 다음 위치~마지막 까지의 최소값,현재 위치에서 경찰차 2이 다음 사건까지 움직인 거리 + 다음 위치~마지막 까지의 최소값)
	경찰차와 목표지점 까지의 거리는 절대값(경찰차 행 - 목표지점 행) +절대값(경찰차 열 - 목표지점 열)
	경찰차의 위치 == 사건의 좌표이므로 현재 몇번째 사건인지 알면 각 경찰차의 좌표를 알 수 있다.
	
	dp가 끝난 후 경로 추적시 현재~마지막 까지의 최소값의 점화식을 이용한다.
	현재 위치에서 경찰차 1이 다음 사건까지 움직인 거리 + 다음 위치~마지막 까지의 최소값 != 현재~마지막 까지의 최소값 -> 현재 위치에선 2를 움직인 것이 최소
	현재 위치에서 경찰차 1이 다음 사건까지 움직인 거리 + 다음 위치~마지막 까지의 최소값 == 현재~마지막 까지의 최소값 -> 현재 위치에선 1를 움직인 것이 최소
	
	bottom-top으로 순서대로 출력하면 해결
	
	풀이 팁 : dp문제에서 bottom-top vs top-bottom의 선택 기준은 시작 위치가 명확한지 vs 목표 위치가 명확한지이다.
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

	public static int getMinDistance(int curr, int police1, int police2) { //사건 curr번 이고 경찰의 위치가 police1,police2 일때 마지막 사건 까지의 최소값
		if (curr > W) { //사건 번호가 주어진 개수보다 커졌을때 -> 모든 사건을 끝냈을 때
			return 0; //경찰차를 안움직여도 되므로 움직인 거리는 0이다.
		} else if (dp[police1][police2] != 0) { // 이미 최소값을 구했을 때
			return dp[police1][police2];
		} else {
			Coordinate police1Coord = police1 == 0 ? new Coordinate(1, 1) : events[police1]; // 현재 경찰 1 위치 : 0은 초기값이므로 경찰1의 위치는 1,1
			Coordinate police2Coord = police2 == 0 ? new Coordinate(N, N) : events[police2]; // 현재 경찰 2 위치 : 0은 초기값이므로 경찰1의 위치는 N,N
			
			//min(현재 위치에서 police1이 출동했을 때,현재 위치에서 police2가 출동했을 때)
			//-> min(다음 사건에서 경찰의 위치가 police1이 [현재 사건위치]이고 police 2가 움직이지 않았을때 최소거리 + 사건번호 curr로 움직인 거리, 
			//다음 사건에서 경찰의 위치가 police2가 [현재 사건위치]이고 police 1가 움직이지 않았을때 최소거리 + 사건번호 curr로 움직인 거리)
			dp[police1][police2] = Math.min(
					getMinDistance(curr + 1, curr, police2) + getDistance(police1Coord, events[curr]),
					getMinDistance(curr + 1, police1, curr) + getDistance(police2Coord, events[curr]));
					
			return dp[police1][police2];
		}
	}

	public static int getDistance(Coordinate from, Coordinate to) { //두 좌표간의 거리를 구하는 매소드
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
