import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
	풀이 알고리즘
	섬의 높이 별로 물에 잠길때 변화가 생기므로 섬의 높이를 중복없이 저장하여 개수 만큼 dfs 반복
*/
public class Main {
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static boolean visited[][];
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		TreeSet<Integer> heightSet = new TreeSet<>(); // 높이를 중복없이 낮은 순서로 정렬하기 위한 set
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				heightSet.add(map[i][j]);
			}
		}

		int max = 1;

		for (int height : heightSet) {
			visited = new boolean[N][N];
			int areaCnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] || map[i][j] <= height) { // 방문 했거나 현재 높이보다 낮은 건물이라면 패스
						continue;
					}

					moveTallerThanFloor(new Coordinate(i, j), height);
					areaCnt++; // 영역 개수 증가
				}
			}
			max = Math.max(areaCnt, max);
		}
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void moveTallerThanFloor(Coordinate curr, int floor) { // 홍수보다 높이가 높은 건물만 dfs로 탐색(현재 좌표, 홍수 높이)
		visited[curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				continue;
			}
			if (visited[next.row][next.col] || map[next.row][next.col] <= floor) {
				continue;
			}
			moveTallerThanFloor(next, floor);
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