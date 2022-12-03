import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int board[][];
	static boolean visited[][][];
	static ArrayList<ArrayList<Pipe>> moveStragy = new ArrayList<>();
	static int count[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int width = 0;
		int height = 1;
		int diag = 2;

		for (int i = 0; i < 3; i++) {
			moveStragy.add(new ArrayList<Pipe>());
		}

		moveStragy.get(width).add(new Pipe(0, 1, width));
		moveStragy.get(width).add(new Pipe(1, 1, diag));
		moveStragy.get(height).add(new Pipe(1, 0, height));
		moveStragy.get(height).add(new Pipe(1, 1, diag));
		moveStragy.get(diag).add(new Pipe(0, 1, width));
		moveStragy.get(diag).add(new Pipe(1, 0, height));
		moveStragy.get(diag).add(new Pipe(1, 1, diag));

		int size = Integer.valueOf(br.readLine());

		board = new int[size][size];
		count = new int[3][size][size];
		visited = new boolean[3][size][size];
		for (int i = 0; i < board.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		Pipe start = new Pipe(0, 1, width);
		count[width][start.row][start.col] = 1;

		dfs(start);
		int sum = 0;
		for (int i = 0; i < count.length; i++) {
			sum += count[i][size - 1][size - 1];
		}
		bw.write(String.valueOf(sum));
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

	static public void dfs(Pipe curr) {
		int size = board.length;
		for (int i = 0; i < moveStragy.get(curr.status).size(); i++) {
			Pipe nextStragy = moveStragy.get(curr.status).get(i);
			Pipe next = new Pipe(curr.row + nextStragy.row, curr.col + nextStragy.col, nextStragy.status);
			if (!isValid(next, size)) {
				continue;
			}
			if (next.status == 2) {
				if (!isValid(new Coordinate(next.row - 1, next.col), size)
						|| !isValid(new Coordinate(next.row, next.col - 1), size)) {
					continue;
				}
			}
			count[next.status][next.row][next.col]++;
			dfs(next);
		}
	}

	static public boolean isValid(Coordinate curr, int size) {
		if (curr.row < 0 || curr.col < 0 || curr.col >= size || curr.row >= size) {
			return false;
		}
		if (board[curr.row][curr.col] != 0) {
			return false;
		} else {
			return true;
		}
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}

class Pipe extends Coordinate {
	int status;

	public Pipe(int row, int col, int status) {
		this.row = row;
		this.col = col;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pipe [status=" + status + ", row=" + row + ", col=" + col + "]";
	}

}