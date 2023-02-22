import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int map[][];
	static boolean visited[][];
	static int K;
	static int maxLength;
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			Queue<Coordinate> topQ = init();

			while (!topQ.isEmpty()) {
				Coordinate top = topQ.poll();
				visited = new boolean[map.length][map[0].length];
				findRoute(top, 1, 0);
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(testcase).append(" ").append(maxLength).append("\n");
			bw.write(sb.toString());
		}

		bw.flush();
		bw.close();
	}

	public static void findRoute(Coordinate curr, int length, int construction) {
		maxLength = Math.max(length, maxLength);
		visited[curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) { // 벗어나면 다음 방향
				continue;
			}

			if (construction == 0 && !visited[next.row][next.col]) {
				for (int height = 1; height <= K; height++) {// 아직 공사를 하지 않았고 다음 높이가 공사하지 않았을 때
														// 방문하지
														// 않았고 다음 높이에서 공사 했을 때의 높이가 현재보다 낮다면
					if (map[next.row][next.col] - height < map[curr.row][curr.col]) {
						visited[next.row][next.col] = true;
						map[next.row][next.col] = map[next.row][next.col] - height;
						
						findRoute(next, length + 1, 1);
						
						visited[next.row][next.col] = false;
						map[next.row][next.col] = map[next.row][next.col] + height;
					}
				}

			}
			if (!visited[next.row][next.col] && map[next.row][next.col] < map[curr.row][curr.col]) { // 다음을 방문하지 않았고 다음
				visited[next.row][next.col] = true;
				findRoute(next, length + 1, construction);
				visited[next.row][next.col] = false;
			}
		}

		visited[curr.row][curr.col] = false;

	}

	public static Queue<Coordinate> init() throws IOException { // 최소값, 집, 회사, 고객 좌표 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 행,열 크기
		K = Integer.valueOf(st.nextToken()); // 깊이
		visited = new boolean[N][N]; // 방문 배열( 행, 열)
		map = new int[N][N];
		maxLength = 1;

		int max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				max = Math.max(map[i][j], max);
			}
		}

		Queue<Coordinate> topQ = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == max) {
					topQ.add(new Coordinate(i, j));
				}
			}
		}

		return topQ;

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

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}