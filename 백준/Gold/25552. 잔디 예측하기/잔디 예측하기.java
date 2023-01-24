import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char map[][];
	static char answer[][];
	static boolean visited[][][];
	static int spread;
	static Queue<Coordinate> q = new LinkedList<>();
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());

		map = new char[row][col];
		answer = new char[row][col];

		for (int i = 0; i < row; i++) {
			char location[] = br.readLine().toCharArray();

			for (int j = 0; j < col; j++) {
				map[i][j] = location[j];
				if (map[i][j] == 'O') {
					q.add(new Coordinate(i, j));
				}
			}
		}

		spread = Integer.valueOf(br.readLine());
		visited = new boolean[spread + 1][row][col];

		for (int i = 0; i < row; i++) {
			char location[] = br.readLine().toCharArray();

			for (int j = 0; j < col; j++) {
				answer[i][j] = location[j];
			}
		}

		while (!q.isEmpty()) {
			Coordinate curr = q.poll();

			visited[0][curr.row][curr.col] = true;
			move(curr, 0);
		}

		boolean isValid = true;

		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				if (answer[i][j] != map[i][j]) {
					isValid = false;
					break;
				}
			}
			if (!isValid) {
				break;
			}
		}

		if (isValid) {
			System.out.println("YES");
		} else {

			System.out.println("NO");
		}
	}

	public static void move(Coordinate curr, int cnt) {
		if (cnt == spread) {
			return;
		}

		visited[cnt][curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length
					|| visited[cnt + 1][next.row][next.col]) {
				continue;
			}
			if (answer[next.row][next.col] == 'O' && map[next.row][next.col] == 'X') {
				map[next.row][next.col] = 'O';
				q.add(next);
			} else {
				move(next, cnt + 1);
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

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
}