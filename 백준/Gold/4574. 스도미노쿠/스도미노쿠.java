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
 *		->해시 처리가 잘 안됐음
 *
 *		두 수로 2자리수를 만들어 방문 처리
 *
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

	public static void printArr() {	//결과 출력하는 메소드
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static boolean domino(int idx) {	//사용한 도미노를 파악하는 메소드
		if (idx == emptyList.size()) {
			return true;
		}

		Coordinate coord = emptyList.get(idx);

		if (coordVisited[coord.row][coord.col]) {	// 이미 방문한 좌표라면
			return domino(idx + 1);
		}

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(coord.row + upDown[k], coord.col + leftRight[k]);
			
			//배열 밖으로 벗어난 경우
			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				continue;
			}

			//이미 사용한 좌표인 경우
			if (coordVisited[next.row][next.col]) {
				continue;
			}
			
			//이미 사용한 도미노인 경우(2자리수로 표현)
			if (dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]]) {
				continue;
			}
			
			coordVisited[coord.row][coord.col] = true;
			coordVisited[next.row][next.col] = true;
			dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]] = true;
			dominoVisited[map[next.row][next.col] + map[coord.row][coord.col] * 10] = true;
			
			//다음 도미노가 가능하면 현재도 가능함
			if (domino(idx + 1)) {
				return true;
			}

			coordVisited[coord.row][coord.col] = false;
			coordVisited[next.row][next.col] = false;
			dominoVisited[map[next.row][next.col] * 10 + map[coord.row][coord.col]] = false;
			dominoVisited[map[next.row][next.col] + map[coord.row][coord.col] * 10] = false;
		}
		//상하좌우로 도미노를 사용하지 못하는 경우
		return false;
	}
	
	//스도쿠를 성립하는지 파악하는 메소드
	public static boolean sudoku(int idx) {
		if (idx == emptyList.size()) {
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
	
	//해당 숫자가 스도쿠 규칙에 맞게 빈칸에 들어갈 수 있는지 파악하는 메소드
	public static boolean check(Coordinate coord, int val) {
		//행 체크
		for (int i = 0; i < map.length; i++) {
			if (map[i][coord.col] == val) {
				return false;
			}
		}
		
		//열 체크
		for (int i = 0; i < map[0].length; i++) {
			if (map[coord.row][i] == val) {
				return false;
			}
		}
		
		//중앙 좌표 구하는 부분
		int centerRow = (coord.row / 3) * 3 + 1;
		int centerCol = (coord.col / 3) * 3 + 1;

		int upDown[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int leftRight[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
		
		
		//3x3 영역 체크
		for (int i = 0; i < upDown.length; i++) {
			if (map[centerRow + upDown[i]][centerCol + leftRight[i]] == val) {
				return false;
			}
		}

		return true;
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