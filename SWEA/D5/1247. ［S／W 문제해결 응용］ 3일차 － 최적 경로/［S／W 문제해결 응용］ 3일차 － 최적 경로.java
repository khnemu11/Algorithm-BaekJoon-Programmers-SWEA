import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 최적의 경로를 찾지 말고 모든 경로를 탐색하라고 명시하였으므로 브루트 포스 사용
 * 시작 좌표를 시작으로 모든 고객의 집을 한번씩 방문하고 집 좌표로 이동 -> 10P10 = 10! = 3,628,800(각 테스트 케이스 별로 2초이내어야 하므로 가능)
 * */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Coordinate company;
	static Coordinate house;
	static Coordinate customers[];
	static boolean visited[];
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			init();
			selectCustomer(company, 0, 0);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(testcase).append(" ").append(min).append("\n");

			bw.write(sb.toString());
		}

		bw.flush();
		bw.close();
	}

	public static void init() throws IOException { //최소값, 집, 회사, 고객 좌표 저장
		int N = Integer.valueOf(br.readLine());
		customers = new Coordinate[N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());

		company = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		house = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

		for (int i = 0; i < N; i++) {
			customers[i] = new Coordinate(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}
	}

	public static void selectCustomer(Coordinate curr, int count, int distance) { //고객을 선택하고 마지막에 집을 선택해 거리를 구하는 메소드 (현재 위치, 고른 고객의 수, 현재까지 이동한 거리)
		if (count == customers.length) {
			distance = distance + Math.abs(curr.row - house.row) + Math.abs(curr.col - house.col);
			min = Math.min(min, distance);
		} else {
			for (int i = 0; i < customers.length; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				selectCustomer(customers[i], count + 1,
						distance + Math.abs(curr.row - customers[i].row) + Math.abs(curr.col - customers[i].col));
				visited[i] = false;
			}
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