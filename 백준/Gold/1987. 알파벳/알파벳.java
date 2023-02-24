import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
	풀이 알고리즘
	4방향으로 방문하지 않은 알파벳만 방문하여 백트래킹-dfs로 해결
*/
public class Main {
	static int maxMove = 0;
	static char map[][];
	static boolean visited[] = new boolean[26];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			char input[] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];
			}
		}

		moveAlpha(new Coordinate(0, 0), 1);

		bw.write(maxMove + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void moveAlpha(Coordinate curr, int length) { // 방문하지 않은 알파벳으로 이동하는 메소드
		maxMove = Math.max(maxMove, length);
		visited[map[curr.row][curr.col] - 'A'] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				continue;
			}
			if (visited[map[next.row][next.col] - 'A']) {
				continue;
			}
			visited[map[next.row][next.col] - 'A'] = true;
			moveAlpha(next, length + 1);
			visited[map[next.row][next.col] - 'A'] = false;
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