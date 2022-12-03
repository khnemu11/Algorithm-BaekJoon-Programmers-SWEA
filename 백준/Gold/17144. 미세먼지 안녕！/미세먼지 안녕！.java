import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int room[][];
	static ArrayList<Coordinate> airCleaners;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int height = row[0];
		int width = row[1];
		int time = row[2];
		room = new int[height][width];
		airCleaners = new ArrayList<>();
		int count = 2;
		for (int i = 0; i < height; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				room[i][j] = Integer.valueOf(st.nextToken());
				if (room[i][j] == -1) {
					airCleaners.add(new Coordinate(i, j));
				}
				count += room[i][j];
			}
		}

		while (time-- > 0) {
			spread();
			count -= room[airCleaners.get(0).row - 1][airCleaners.get(0).col];
			count -= room[airCleaners.get(1).row + 1][airCleaners.get(1).col];
			clean();
			// 먼지 확산
			// 청소
		}
		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
	}

	public static void spread() {
		Queue<Dust> dustQueue = new LinkedList<>();
		for (int row = 0; row < room.length; row++) {
			for (int col = 0; col < room[0].length; col++) {
				if (room[row][col] < 5) {
					continue;
				}
				dustQueue.add(new Dust(row, col, room[row][col]));
			}
		}

		while (!dustQueue.isEmpty()) {
			Dust curr = dustQueue.poll();
			int count = 0;
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };

			for (int k = 0; k < upDown.length; k++) {
				Dust next = new Dust(curr.row + upDown[k], curr.col + leftRight[k], curr.amount / 5);

				if (next.row < 0 || next.col < 0 || next.row >= room.length || next.col >= room[0].length
						|| room[next.row][next.col] == -1) {
					continue;
				}

				room[next.row][next.col] += next.amount;
				count++;
			}

			room[curr.row][curr.col] -= curr.amount / 5 * count;
		}
	}

	public static void clean() {
		Coordinate upCleaner = airCleaners.get(0);
		Coordinate downCleaner = airCleaners.get(1);
		int upCleanerDirectionRow[] = { -1, 0, 1, 0 };
		int upCleanerDirectionCol[] = { 0, 1, 0, -1 };
		int downCleanerDirectionRow[] = { 1, 0, -1, 0 };
		int downCleanerDirectionCol[] = { 0, 1, 0, -1 };

		// 공기청정기 위의 값 삭제
		room[upCleaner.row - 1][upCleaner.col] = 0;
		Coordinate next = new Coordinate(upCleaner.row, upCleaner.col);
		for (int k = 0; k < 4; k++) {
			next.row += upCleanerDirectionRow[k];
			next.col += upCleanerDirectionCol[k];

			while (true) {
				if (next.row < 0 || next.col < 0 || next.row > upCleaner.row || next.col >= room[0].length) {
					next.row -= upCleanerDirectionRow[k];
					next.col -= upCleanerDirectionCol[k];
					break;
				}

				room[next.row - upCleanerDirectionRow[k]][next.col
						- upCleanerDirectionCol[k]] = room[next.row][next.col];
				next.row += upCleanerDirectionRow[k];
				next.col += upCleanerDirectionCol[k];
			}
		}
		room[upCleaner.row][upCleaner.col] = -1;

		room[downCleaner.row + 1][downCleaner.col] = 0;
		next = new Coordinate(downCleaner.row, downCleaner.col);

		for (int k = 0; k < 4; k++) {
			next.row += downCleanerDirectionRow[k];
			next.col += downCleanerDirectionCol[k];
			while (true) {
				if (next.row < downCleaner.row || next.col < 0 || next.row >= room.length
						|| next.col >= room[0].length) {
					next.row -= downCleanerDirectionRow[k];
					next.col -= downCleanerDirectionCol[k];
					break;
				}
//				System.out.println(next.toString() + " -> " + (next.row - downCleanerDirectionRow[k]) + " , "
//						+ (next.col - downCleanerDirectionCol[k]));
				room[next.row - downCleanerDirectionRow[k]][next.col
						- downCleanerDirectionCol[k]] = room[next.row][next.col];
				next.row += downCleanerDirectionRow[k];
				next.col += downCleanerDirectionCol[k];
			}
		}

		room[downCleaner.row][downCleaner.col] = -1;
	}

	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class Coordinate {
	int row;
	int col;

	Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "[ row : " + row + " , col : " + col + " ]";
	}
}

class Dust extends Coordinate {
	int amount;

	public Dust(int row, int col, int amount) {
		this.row = row;
		this.col = col;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Dust [amount=" + amount + ", row=" + row + ", col=" + col + "]";
	}

}