import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 반복되는 구간이 나오면 이전 폭탄 보드와 동일함
 * 링크드 리스트를 통해 부모에 이전 폭탄상태를 저장
 * 
 * */

public class Main {
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int targetTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		targetTime = Integer.valueOf(st.nextToken());
		char board[][] = new char[height][width];

		for (int i = 0; i < height; i++) {
			String input = br.readLine();
			for (int j = 0; j < width; j++) {
				board[i][j] = input.charAt(j);
			}
		}

		char resultBoard[][] = new char[height][width];

		if (targetTime % 2 == 0) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					resultBoard[i][j] = 'O';
				}
			}
		} else {

			Game game = new Game(board, 1);
			resultBoard = process(new Game(board, 1));
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				bw.write(resultBoard[i][j]);
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static char[][] process(Game game) {
//		System.out.println(game.time);
//		printArr(game.board);
		if (game.time == targetTime) {
			return game.board;
		}

		Game next = new Game(explode(game.board), game.time + 2);
		next.prev = game;
		
		if (game.prev == null) {
			return process(next);
		}
		if (isEquals(game.prev.board, next.board)) {
			if ((targetTime - next.time) % 4 == 0) {
				return next.board;
			} else {
				return game.board;
			}
		} else {
			game.prev = null;
			return process(next);
		}
	}

	public static void printArr(char map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isEquals(char board1[][], char board2[][]) {
//		printArr(board1);
//		System.out.println("VS");
//		printArr(board2);
		if (board1 == null) {
			return false;
		}
		for (int i = 0; i < board1.length; i++) {
			for (int j = 0; j < board1[0].length; j++) {
				if (board1[i][j] != board2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	ArrayList<Integer> list;

	public static char[][] explode(char board[][]) {
		char temp[][] = copyArr(board);

		Queue<Coordinate> bombQ = new LinkedList<>();

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (temp[i][j] == 'O') {
					bombQ.add(new Coordinate(i, j));
				}
			}
		}

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = 'O';
			}
		}

		while (!bombQ.isEmpty()) {
			Coordinate bomb = bombQ.poll();
			temp[bomb.row][bomb.col] = '.';

			for (int k = 0; k < dx.length; k++) {
				Coordinate next = new Coordinate(bomb.row + dx[k], bomb.col + dy[k]);

				if (next.row < 0 || next.col < 0 || next.row >= board.length || next.col >= board[0].length) {
					continue;
				}

				temp[next.row][next.col] = '.';
			}
		}
		return temp;
	}

	public static char[][] copyArr(char board[][]) {
		char temp[][] = new char[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				temp[i][j] = board[i][j];
			}
		}

		return temp;
	}
}

class Game {
	char board[][];
	int time;
	Game prev;

	public Game(char board[][], int time) {
		this.board = board;
		this.time = time;
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