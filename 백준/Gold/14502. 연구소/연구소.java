import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static ArrayList<Coordinate> empty = new ArrayList<>();
	static ArrayList<Coordinate> candidate = new ArrayList<>();
	static ArrayList<Coordinate> virus = new ArrayList<>();
	static int map[][];
	static int temp[][];
	static boolean visited[][];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int row[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		map = new int[row[0]][row[1]];

		for (int i = 0; i < map.length; i++) {
			row = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = row[j];

				if (row[j] == 0) {
					empty.add(new Coordinate(i, j));
				} else if (row[j] == 2) {
					virus.add(new Coordinate(i, j));
				}
			}
		}

		makeWall(0, 3, 0);
		bw.write(String.valueOf(max));
		bw.newLine();
		bw.flush();
	}

	public static void printArray(int map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void makeWall(int curr, int depth, int start) {
		if (curr == depth) {
			visited = new boolean[map.length][map[0].length];
			temp = new int[map.length][map[0].length];
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[0].length; j++) {
					temp[i][j] = map[i][j];
				}
			}

			for (Coordinate wall : candidate) {
				temp[wall.row][wall.col] = 1;
			}

			for (Coordinate virusStart : virus) {
				if (visited[virusStart.row][virusStart.col]) {
					continue;
				}
				spread(virusStart);
			}

			int count = 0;
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[0].length; j++) {
					if (temp[i][j] == 0) {
						count++;
					}
				}
			}
//
//			printArray(temp);
//			System.out.println("count : " + count);
//			System.out.println();
			max = Math.max(max, count);
		}

		else {
			for (int i = start; i < empty.size(); i++) {
				candidate.add(empty.get(i));
				makeWall(curr + 1, depth, i + 1);
				candidate.remove(candidate.size() - 1);
			}
		}
	}

	public static void spread(Coordinate curr) {
		visited[curr.row][curr.col] = true;
		int rowMove[] = { -1, 1, 0, 0 };
		int colMove[] = { 0, 0, -1, 1 };

		for (int k = 0; k < rowMove.length; k++) {
			int nextRow = rowMove[k] + curr.row;
			int nextCol = colMove[k] + curr.col;

			if (nextRow < 0 || nextCol < 0 || nextRow >= temp.length || nextCol >= temp[0].length
					|| visited[nextRow][nextCol]) {
				continue;
			}
			if (temp[nextRow][nextCol] == 0) {
				temp[nextRow][nextCol] = 2;
				visited[nextRow][nextCol] = true;
				spread(new Coordinate(nextRow, nextCol));
			}
		}
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
		return "[ row : " + row + " , col : " + col + " ]";
	}
}
