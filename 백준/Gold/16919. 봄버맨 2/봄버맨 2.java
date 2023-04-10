import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	static int targetTime;	//목표 시간

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
		if (game.time == targetTime) { // 목표 시간에 다다랐으면 해당 폭탄 상태를 리턴
			return game.board;
		}

		Game next = new Game(explode(game.board), game.time + 2); // 다음 폭탄상태를 시간과 함께 저장
		next.prev = game;

		if (game.prev == null) { // 만약 부모의 부모가 비어있으면 다음 폭탄과 비교불가 하므로 다음 과정으로
			return process(next);
		}
		if (isEquals(game.prev.board, next.board)) { // 다음 폭탄 상태와 부모의 부모 노드와 같다면 반복되는 패턴
			if ((targetTime - next.time) % 4 == 0) {
				return next.board;
			} else {
				return game.board;
			}
		} else { // 같지 않다면 다음 폭탄으로
			game.prev = null; // 부모, 조부모, 자식만 알면 되므로 그 이상의 폭탄상태는 제거(메모리 초과 방지)
			return process(next);
		}
	}

	// 2개의 2차원 char 배열이 같은이 확인하는 메소드
	public static boolean isEquals(char board1[][], char board2[][]) {
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
	// 폭탄을 모두 심고 기존의 폭탄만 4방향으로 터뜨리는 메소드
	public static char[][] explode(char board[][]) {
		char temp[][] = copyArr(board);

		Queue<Coordinate> bombQ = new LinkedList<>();
		
		//기존 폭탄 위치 확인
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (temp[i][j] == 'O') {
					bombQ.add(new Coordinate(i, j));
				}
			}
		}
		//폭탄 모두 심기
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = 'O';
			}
		}
		//기존 폭탄 터뜨리기
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
	//2차원 배열을 깊은 복사하는 메소드
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
//폭탄 상태를 저장하는 링크트 리스트의 노드 클래스
class Game {
	char board[][];	//폭탄 상태
	int time;	//걸린 시간
	Game prev;	//이전 폭탄

	public Game(char board[][], int time) {
		this.board = board;
		this.time = time;
	}
}
//좌표 클래스
class Coordinate {	
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
