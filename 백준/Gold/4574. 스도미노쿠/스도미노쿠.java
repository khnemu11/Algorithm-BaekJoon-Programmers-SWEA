import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 *	1) 스도쿠 풀이
 *		행, 열, 3x3 위치 파악
 *	2) 같은 도미노가 있는지 확인
 *		작은수+큰수 조합의 해쉬 set을 이용해 해결 
 * 
 * */

public class Main {
	static ArrayList<Coordinate> emptyList;
	static int map[][];
	static boolean coordVisited[][];
	static boolean dominoVisited[];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int idx = 1;

		while (true) {
			emptyList = new ArrayList<>();
			map = new int[9][9];
			coordVisited = new boolean[9][9];
			dominoVisited = new boolean[100];
			int N = Integer.valueOf(br.readLine());

			if (N == 0) {
				break;
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				int val1 = Integer.valueOf(st.nextToken());
				String location1 = st.nextToken();

				int row1 = location1.charAt(0) - 'A';
				int col1 = location1.charAt(1) - '1';

				map[row1][col1] = val1;
				int val2 = Integer.valueOf(st.nextToken());
				String location2 = st.nextToken();

				int row2 = location2.charAt(0) - 'A';
				int col2 = location2.charAt(1) - '1';

				map[row2][col2] = val2;

				coordVisited[row1][col1] = true;
				coordVisited[row2][col2] = true;

				dominoVisited[val1 * 10 + val2] = true;
				dominoVisited[val2 * 10 + val1] = true;
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				String location = st.nextToken();

				int row = location.charAt(0) - 'A';
				int col = location.charAt(1) - '1';

				map[row][col] = i;
				coordVisited[row][col] = true;
			}

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == 0) {
						emptyList.add(new Coordinate(i, j));
					}
				}
			}
			sudoku(0);
			System.out.println("Puzzle "+idx);
			printArr();
			idx++;
		}

		bw.flush();
	}

	public static void printArr() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void printArr(boolean map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean domino(int idx) {
		if (idx == emptyList.size()) {
//			System.out.println("전부 탐색 했는지 확인");
//			for (int i = 0; i < map.length; i++) {
//				for (int j = 0; j < map[0].length; j++) {
//					if (!dominoVisited[i][j]) {
//						return false;
//					}
//				}
//			}
			return true;
		}

		Coordinate coord = emptyList.get(idx);

//		System.out.println("curr : " + coord);
		if (coordVisited[coord.row][coord.col]) {
//			System.out.println(coord + "는 방문");
			return domino(idx + 1);
		}

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(coord.row + upDown[k], coord.col + leftRight[k]);

			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
//				System.out.println("배열 나감");
				continue;
			}

//			System.out.println(coord + " with " + next);
			if (coordVisited[next.row][next.col]) {
//				System.out.println("이미 사용함");
				continue;
			}

			Domino domino = new Domino(Math.min(map[next.row][next.col], map[coord.row][coord.col]),
					Math.max(map[next.row][next.col], map[coord.row][coord.col]));

			if (dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]]) {
//				System.out.println("도미노 중복");
				continue;
			}
//			System.out.println(coord + " with " + next + " 성공");
			coordVisited[coord.row][coord.col] = true;
			coordVisited[next.row][next.col] = true;
			dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]] = true;
			dominoVisited[map[next.row][next.col] + map[coord.row][coord.col] * 10] = true;

			if (domino(idx + 1)) {
				return true;
			}

			coordVisited[coord.row][coord.col] = false;
			coordVisited[next.row][next.col] = false;
			dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]] = false;
			dominoVisited[map[next.row][next.col] + map[coord.row][coord.col] * 10] = false;
		}
//		System.out.println(coord + " 불가 ");
		return false;
	}

	public static boolean sudoku(int idx) {
		if (idx == emptyList.size()) {
//			System.out.println("스도쿠 끝 ");
//			printArr();
			return domino(0);
		}

		Coordinate curr = emptyList.get(idx);

		for (int val = 1; val <= 9; val++) {
			if (!check(curr, val)) {
				continue;
			}

			map[curr.row][curr.col] = val;

			if (sudoku(idx + 1)) {
				return true;
			}

			map[curr.row][curr.col] = 0;
		}

		return false;
	}

	public static boolean check(Coordinate coord, int val) {
		for (int i = 0; i < map.length; i++) {
			if (map[i][coord.col] == val) {
				return false;
			}
		}
		for (int i = 0; i < map[0].length; i++) {
			if (map[coord.row][i] == val) {
				return false;
			}
		}

		int centerRow = (coord.row / 3) * 3 + 1;
		int centerCol = (coord.col / 3) * 3 + 1;

		int upDown[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int leftRight[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

		for (int i = 0; i < upDown.length; i++) {
			if (map[centerRow + upDown[i]][centerCol + leftRight[i]] == val) {
				return false;
			}
		}

		return true;
	}
}

class Domino {
	int small;
	int big;

	public Domino(int small, int big) {
		this.small = small;
		this.big = big;
	}

	@Override
	public int hashCode() {
		return small + big * 10;
	}

	@Override
	public boolean equals(Object obj) {
		Domino o = (Domino) obj;

		if (this.small == o.small && this.big == o.big) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Domino [small=" + small + ", big=" + big + "]";
	}

}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String toString() {
		return row + " , " + col;
	}
}