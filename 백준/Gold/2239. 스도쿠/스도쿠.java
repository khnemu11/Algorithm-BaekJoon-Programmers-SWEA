import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int board[][];
	static ArrayList<Coordinate> problemList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		board = new int[9][9];

		for (int i = 0; i < 9; i++) {
			int row[] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.valueOf(row[j]);
				if (board[i][j] == 0) {
					problemList.add(new Coordinate(i, j));
				}
			}
		}
		solveSudoku(0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.newLine();
		bw.flush();
	}

	public static boolean solveSudoku(int currIndex) {
		if (currIndex == problemList.size()) {
			return true;
		}
		Coordinate curr = problemList.get(currIndex);
//		for (int i = currIndex; i >= 0; i--) {
//			System.out.print(">");
//		}
		boolean isFind = false;
		for (int i = 1; i < 10; i++) {
			curr.value = i;
			board[curr.row][curr.col] = i;
//			System.out.println("Curr : " + curr.toString());
			if (!isValid(curr)) {
				board[curr.row][curr.col] = 0;
				continue;
			}

			if (solveSudoku(currIndex + 1)) {
				isFind = true;
				break;
			} else {
				board[curr.row][curr.col] = 0;
			}
		}
//		System.out.println("no answer in " + curr.toString());
		return isFind;
	}

	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isValid(Coordinate curr) {
		int upDown[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int leftRight[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
//		System.out.println(curr.toString());
//		printArr(board);
		// 세로
		boolean visited[] = new boolean[10];
		for (int i = 0; i < board.length; i++) {
			if (board[i][curr.col] == 0) {
				continue;
			}

			if (visited[board[i][curr.col]]) {
//				System.out.println("col invalid");
				return false;
			}
			visited[board[i][curr.col]] = true;

		}
//		System.out.println("col pass");
		// 가로
		visited = new boolean[10];
		for (int i = 0; i < board[0].length; i++) {
			if (board[curr.row][i] == 0) {
				continue;
			}
			if (visited[board[curr.row][i]]) {
//				System.out.println("row invalid");
				return false;
			}
			visited[board[curr.row][i]] = true;
		}

//		System.out.println("row pass");
		// 9X9방향

		Coordinate center = new Coordinate((curr.row / 3) * 3 + 1, (curr.col / 3) * 3 + 1);
		visited = new boolean[10];
		for (int i = 0; i < upDown.length; i++) {
			Coordinate next = new Coordinate(center.row + upDown[i], center.col + leftRight[i]);
			next.value = board[next.row][next.col];
			if (next.value == 0) {
				continue;
			}
			if (visited[next.value]) {
//				System.out.println("3x3 invalid");
				return false;
			}
			visited[next.value] = true;
		}

//		System.out.println("3*3 pass");

		return true;
	}
}

class Coordinate {
	int row;
	int col;
	int value;

	Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Coordinate(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + ", value=" + value + "]";
	}

}
