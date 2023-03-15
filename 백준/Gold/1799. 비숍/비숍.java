package defalut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	비숍을 오른쪽으로 한개씩 골라 대각선으로 겹치는 것이 있는 것을 가지치기하고 탐색하는 방법
	전부 다 탐색한다고 가정 시 2^(N*N) = 2^100 -> 시간 초과
	
	검은색과 흰색의 칸은 서로 영향을 끼치지 않으므로 흰색과 검은색을 따로 시작
	2^(N/2*N/2) * 2 -> 2^26 -> 시간내에 가능
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
		selectBishop(BLACK, 0, 0, 0);
		selectBishop(WHITE, 0, 0, 1);

		bw.write((max[0] + max[1]) + "\n");
		bw.flush();
	}

	public static void selectBishop(boolean color, int idx, int cnt, int colorIdx) { // 조건에 맞는 비숍을 선택하는 메소드 (색 참: 검은색,
																						// 거짓, 흰색, 현재 비숍 좌표, 가능한 비숍 개수,
																						// 현재 색의 인덱스)
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

	public static boolean isInRange(Coordinate coord) { // 왼쪽 위 대각선으로 갈 때 겹치는 비숍이 있는지 확인하는 메소드
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

	public static boolean outOfArray(int map[][], Coordinate coord) { // 배열 밖으로 좌표가 넘어가는지 확인하는 메소드
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
