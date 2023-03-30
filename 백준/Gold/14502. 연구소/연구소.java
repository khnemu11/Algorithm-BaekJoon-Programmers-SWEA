import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
    1) 안전 영역 = 초기 안전영역개수 - 퍼진 바이러수 개수 - 새롭게 새운 벽 개수(3)
    2) 초기 안전영역 개수 구하기
	3) 바이러스 3개 위치를 dfs를 이용해 고르기
	4) 바이러스를 bfs를 이용해 퍼뜨리기(4방향 벡터 이용)
	5) 바이러스가 퍼진 횟수를 구하기
    6) 안전영역 개수를 구하고 최대값 저장
*/

public class Main {
	static int map[][];
	static boolean visited[][];
	static ArrayList<Coordinate> virusInit = new ArrayList<>();
	static int WALL_NUM = 3;
	static int max = 0;
	static int safeZoneCntInit = 0;
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		Coordinate start = null;
		map = new int[height][width];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] == 0) {
					safeZoneCntInit++;
					if (start == null) {
						start = new Coordinate(i, j);
					}
				} else if (map[i][j] == 2) {
					virusInit.add(new Coordinate(i, j));
				}
			}
		}
		pick(0, start, new ArrayList<Coordinate>());

		bw.write("" + max);
		bw.flush();
	}

	public static void pick(int n, Coordinate start, ArrayList<Coordinate> walls) {
		if (n == WALL_NUM) {
			visited = new boolean[map.length][map[0].length];
			int virusZoneCnt = spread();
			int safeZoneCnt = safeZoneCntInit - WALL_NUM - virusZoneCnt;

			max = Math.max(max, safeZoneCnt);

		} else {
			for (int row = start.row; row < map.length; row++) {
				for (int col = row == start.row ? start.col : 0; col < map[0].length; col++) {
					if (map[row][col] == 0) {
						map[row][col] = 1;
						walls.add(new Coordinate(row, col));

						pick(n + 1, new Coordinate(row, col), walls);

						walls.remove(walls.size() - 1);
						map[row][col] = 0;
					}
				}
			}
		}
	}

	public static int spread() {
		Queue<Coordinate> virusQ = new LinkedList<>();

		int cnt = 0;
		for (Coordinate virus : virusInit) {
			virusQ.add(virus);
		}
		while (!virusQ.isEmpty()) {
			Coordinate curr = virusQ.poll();
			visited[curr.row][curr.col] = true;

			for (int k = 0; k < upDown.length; k++) {
				Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

				if (next.row < 0 || next.row >= map.length || next.col < 0 || next.col >= map[0].length
						|| visited[next.row][next.col] || map[next.row][next.col] != 0) {
					continue;
				}
				visited[next.row][next.col] = true;
				virusQ.add(next);
				cnt++;
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
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}
