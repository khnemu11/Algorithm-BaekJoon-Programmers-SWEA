import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	dfs 백트래킹으로 전부 확인
 */
public class Main {
	static int map[][];
	static int max[];
	static boolean visited[][];
	static boolean BLACK = true;
	static boolean WHITE = false;
	static boolean isBlack[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		map = new int[N][N];
		max = new int[2];
		visited = new boolean[N][N];
		isBlack = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					isBlack[i][j] = true;
				} else {
					isBlack[i][j] = false;
				}
			}
		}
		selectBishop(BLACK, 0, 0, 0); // 첫번째 칸을 검정으로
		selectBishop(WHITE, 0, 0, 1); // 두번째 칸을 흰색으로

		bw.write((max[0] + max[1]) + "\n");
		bw.flush();
	}

	public static void printArr(boolean arr[][]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void selectBishop(boolean color, int idx, int cnt, int colorIdx) {
		max[colorIdx] = Math.max(max[colorIdx], cnt);

		for (int i = idx; i < map.length * map.length; i++) {
			int row = i / map.length; // 2차원 배열 좌표를 1차원으로 표기
			int col = i % map.length;
			if (isBlack[row][col] == color || map[row][col] == 0 || isInRange(new Coordinate(row, col))) {
				continue;
			}
			visited[row][col] = true;
			selectBishop(color, i + 1, cnt + 1, colorIdx);
			visited[row][col] = false;
		}
	}

	public static boolean isInRange(Coordinate coord) {
		int upDown[] = { -1, -1 };
		int leftRight[] = { -1, 1 };
		for (int k = 0; k < upDown.length; k++) {
			Coordinate nextCoordinate = new Coordinate(coord.row + upDown[k], coord.col + leftRight[k]);

			while (!outOfArray(map, nextCoordinate)) {
				if (visited[nextCoordinate.row][nextCoordinate.col]) {
					return true;
				}
				nextCoordinate.row = nextCoordinate.row + upDown[k];
				nextCoordinate.col = nextCoordinate.col + leftRight[k];
			}
		}

		return false;
	}

	public static boolean outOfArray(int map[][], Coordinate coord) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) {
			return true;
		}
		return false;
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