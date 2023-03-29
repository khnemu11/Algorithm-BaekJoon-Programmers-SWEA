import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* 
 * 	풀이 알고리즘
 * 	
 *	다음 위치로 넘어갈때 마다 개수 증가
 * */

public class Main {
	static Map<Integer, Boolean[]> moveMap = new HashMap<>();
	static int map[][];
	static int cnt = 0;
	static int upDown[] = { 0, 1, 1 };
	static int leftRight[] = { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int WIDTH_MOVE = 0;
		int HEIGHT_MOVE = 1;
		int DIAG_MOVE = 2;

		Boolean width[] = { true, false, true };
		Boolean height[] = { false, true, true };
		Boolean diag_move[] = { true, true, true };

		moveMap.put(WIDTH_MOVE, width);
		moveMap.put(HEIGHT_MOVE, height);
		moveMap.put(DIAG_MOVE, diag_move);

		int N = Integer.valueOf(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		move(new Pipe(WIDTH_MOVE, 0, 1));

		bw.write(cnt + "\n");
		bw.flush();
	}

	public static void move(Pipe pipe) {
//		System.out.println(pipe);
		if (pipe.row == map.length - 1 && pipe.col == map[0].length - 1) {
			cnt++;
//			System.out.println("증가 : " + cnt);
			return;
		}

		Boolean moveStragey[] = moveMap.get(pipe.direction);
		int wallCnt = 0;
		for (int k = 0; k < upDown.length; k++) {
			Pipe next = new Pipe(k, pipe.row + upDown[k], pipe.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				continue;
			}
			if (map[next.row][next.col] == 1) {
				wallCnt++;
				continue;
			}
			if (!moveStragey[k]) {
				continue;
			}
			if (k == 2 && wallCnt > 0) {
				continue;
			}

//			System.out.println(pipe + " -> " + next);
			move(next);
		}
	}
}

class Pipe {
	int direction;
	int row;
	int col;

	public Pipe(int direction, int row, int col) {
		this.direction = direction;
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Pipe [direction=" + direction + ", row=" + row + ", col=" + col + "]";
	}
}
