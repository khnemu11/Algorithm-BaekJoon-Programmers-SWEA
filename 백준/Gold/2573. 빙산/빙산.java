import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 빙산이 존재하는 영역의 4방향 중 0이 있는 영역을 0의 개수만큼 감소 -> bfs
 * 이때 빙산이 0이 된다면 2개 이상의영역으로 나뉘어 지는지 판단 필요 -> dfs
 * 모든 영역이 녹았는데 영역의 개수가 없다면 0 출력
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int N = Integer.valueOf(st.nextToken()); // 행
		int M = Integer.valueOf(st.nextToken()); // 열

		int map[][] = new int[N][M];
		Queue<Coordinate> icebergs = new LinkedList<>();
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
				if (map[i][j] > 0) {
					icebergs.add(new Coordinate(i, j));
				}
			}
		}
		int time = 0;
		int areaNum = getAreaNum(map, icebergs);
		while (areaNum == 1) {
			int loop = icebergs.size();
			Queue<Melt> meltQ = new LinkedList<>();
			while (loop-- > 0) {
				Coordinate curr = icebergs.poll();
				int meltCount = 0;
				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
					if (map[next.row][next.col] == 0) {
						meltCount++;
					}
				}
				if (meltCount > 0) {
					meltQ.add(new Melt(curr.row, curr.col, meltCount));
				} else {
					icebergs.add(curr);
				}
			}

			while (!meltQ.isEmpty()) {
				Melt melt = meltQ.poll();
				map[melt.row][melt.col] = map[melt.row][melt.col] - melt.weight < 0 ? 0
						: map[melt.row][melt.col] - melt.weight;

				if (map[melt.row][melt.col] > 0) {
					icebergs.add(new Coordinate(melt.row, melt.col));
				}
			}

			areaNum = getAreaNum(map, icebergs);
			time++;
		}

		if (areaNum >= 2) {
			bw.write(time + "\n");
		} else {
			bw.write("0\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
	public static int getAreaNum(int map[][], Queue<Coordinate> q) {
		int count = 0;
		boolean visited[][] = new boolean[map.length][map[0].length];

		int loop = q.size();

		while (loop-- > 0) {
			Coordinate curr = q.poll();

			if (!visited[curr.row][curr.col]) {
				getArea(curr, map, visited);
				count++;
			}
			q.add(curr);
		}

		return count;
	}

	public static void getArea(Coordinate curr, int map[][], boolean visited[][]) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		visited[curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
			if (visited[next.row][next.col] || map[next.row][next.col] == 0) {
				continue;
			}

			visited[next.row][next.col] = true;
			getArea(next, map, visited);
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

class Melt extends Coordinate {
	int weight;

	public Melt(int row, int col, int weight) {
		super(row, col);
		this.weight = weight;
	}
}