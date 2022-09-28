import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		char board[][] = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = '.';
			}
		}
		char[] route = br.readLine().toCharArray();

		HashMap<Character, Coordinate> moveMap = new HashMap<>();
		moveMap.put('D', new Coordinate(1, 0, '|'));
		moveMap.put('U', new Coordinate(-1, 0, '|'));
		moveMap.put('L', new Coordinate(0, -1, '-'));
		moveMap.put('R', new Coordinate(0, 1, '-'));
		Coordinate curr = new Coordinate(0, 0, '.');

		for (int i = 0; i < route.length; i++) {
			Coordinate nextCommand = moveMap.get(route[i]);
			Coordinate next = new Coordinate(nextCommand.row + curr.row, nextCommand.col + curr.col,
					nextCommand.symbol);
			if (next.row < 0 || next.row >= size || next.col < 0 || next.col >= size) {
				continue;
			}

			if (board[curr.row][curr.col] == '.') {
				board[curr.row][curr.col] = next.symbol;
			} else if (board[curr.row][curr.col] != '+' && board[curr.row][curr.col] != next.symbol) {
				board[curr.row][curr.col] = '+';
			}

			if (board[next.row][next.col] == '.') {
				board[next.row][next.col] = next.symbol;
			} else if (board[next.row][next.col] != '+' && board[next.row][next.col] != next.symbol) {
				board[next.row][next.col] = '+';
			}
			curr = next;
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		br.close();
	}
}

class Coordinate {
	int row;
	int col;
	char symbol;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + ", symbol=" + symbol + "]";
	}

	public Coordinate(int row, int col, char symbol) {
		this.row = row;
		this.col = col;
		this.symbol = symbol;
	}
}