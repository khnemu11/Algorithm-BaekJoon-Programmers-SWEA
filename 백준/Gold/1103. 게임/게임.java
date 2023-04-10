import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * 0,0 좌표를 시작으로 4방향으로 탐색
 * 이때 방문한 좌표를 다시 방문한 경우 cycle을 형성한 것으로 -1을 저장하고 탐색 종료
 * 현재까지 이동한 횟수를 항상 최대값을 저장
 * */

public class Main {
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1, };
	static int maxMoveCnt = 0;
	static char board[][];
	static int visited[][];
	static boolean isCycle;
	static int height;
	static int width;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.valueOf(st.nextToken());
		width = Integer.valueOf(st.nextToken());

		board = new char[height][width];
		visited = new int[height][width];

		for (int i = 0; i < height; i++) {
			String input = br.readLine();
			for (int j = 0; j < width; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		move(1, new Coordinate(0, 0));
		if (isCycle) {
			bw.write("-1\n");
		} else {
			bw.write(maxMoveCnt + "\n");
		}

		bw.flush();
	}

	public static void move(int cnt, Coordinate coord) {
		if (isCycle) {
			return;
		}
		if (cnt > width * height) {
			isCycle = true;
			return;
		} else {
			visited[coord.row][coord.col] = cnt;
			maxMoveCnt = Math.max(maxMoveCnt, cnt);
			for (int k = 0; k < dx.length; k++) {
				Coordinate next = new Coordinate(coord.row + (board[coord.row][coord.col] - '0') * dx[k],
						coord.col + (board[coord.row][coord.col] - '0') * dy[k]);
				if (next.row < 0 || next.col < 0 || next.row >= board.length || next.col >= board[0].length) {
					continue;
				}
				if (board[next.row][next.col] == 'H') {
					continue;
				}
				if (cnt < visited[next.row][next.col]) {
					continue;
				}

				move(cnt + 1, next);
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
