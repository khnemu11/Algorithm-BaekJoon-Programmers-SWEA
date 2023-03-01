import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘

	
*/
public class Main {
	static int population[][];
	static int N;
	static int x;
	static int y;
	static int d1;
	static int d2;
	static Coordinate top, left, bottom, right;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());

		population = new int[N + 1][N + 1];
		int point[] = new int[4]; // 행,열,d1,d2의 정보가 담긴 배열
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				population[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		pick(0, point);
		System.out.println(minDiff);
	}

	public static void pick(int idx, int point[]) {
		if (idx == point.length) {

			x = point[0];
			y = point[1];
			d1 = point[2];
			d2 = point[3];

			if (!isValid(point)) {
				return;
			}
//			System.out.println(Arrays.toString(point));

			boolean visited[][] = new boolean[N + 1][N + 1];

			top = new Coordinate(x, y);
			bottom = new Coordinate(x + d2 + d1, y - d1 + d2);
			left = new Coordinate(x + d1, y - d1);
			right = new Coordinate(x + d2, y + d2);

			visited[top.row][top.col] = true;
			visited[left.row][left.col] = true;
			visited[right.row][right.col] = true;
			visited[bottom.row][bottom.col] = true;

			setTopLeftBorder(visited);
			setLeftBottomBorder(visited);
			setTopRightBorder(visited);
			setRightBottomBorder(visited);

			Coordinate curr = new Coordinate(top.row, top.col);

			for (int i = 0; i + top.row <= left.row; i++) {
				curr = new Coordinate(top.row + i, top.col - i);
				if (curr.row == top.row && curr.col == top.col) {
					continue;
				}

				int visitedCnt = 0;

				while (visitedCnt < 2) {
					if (visited[curr.row][curr.col]) {
						visitedCnt++;
					}
					visited[curr.row][curr.col] = true;
					curr.col++;
				}
			}

			curr = new Coordinate(left.row, left.col);
//			printArr(visited);
			for (int i = 0; i + left.row <= bottom.row; i++) {
				curr = new Coordinate(left.row + i, left.col + i);
				if (curr.row == bottom.row && curr.col == bottom.col) {
					continue;
				}

				int visitedCnt = 0;
				while (visitedCnt < 2) {

					if (visited[curr.row][curr.col]) {
						visitedCnt++;
					}
					visited[curr.row][curr.col] = true;
					curr.col++;
				}
			}
//			printArr(visited);
			int cnt[] = new int[6];
			int map[][] = new int[N + 1][N + 1];

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (visited[r][c]) {
						cnt[5] += population[r][c];
						map[r][c] = 5;
					} else if (r < left.row && c <= top.col) {
						cnt[1] += population[r][c];
						map[r][c] = 1;
					} else if (r <= right.row && top.col < c) {
						cnt[2] += population[r][c];
						map[r][c] = 2;
					} else if (left.row <= r && c < bottom.col) {
						cnt[3] += population[r][c];
						map[r][c] = 3;
					} else if (right.row < r && bottom.col <= c) {
						cnt[4] += population[r][c];
						map[r][c] = 4;
					}
				}
			}

//			printArr(visited);
//			printArr(map);
			int max = 0;
			int min = Integer.MAX_VALUE;

			for (int i = 1; i < cnt.length; i++) {
				if (cnt[i] > max) {
					max = cnt[i];
				}
				if (min > cnt[i]) {
					min = cnt[i];
				}
			}
//			Arrays.parallelSort(cnt);
//			System.out.println(Arrays.toString(cnt));

			if (min != 0) {
				minDiff = Math.min(max - min, minDiff);
			}

		} else {
			for (int i = 1; i <= N; i++) {
				point[idx] = i;
				pick(idx + 1, point);
			}
		}
	}

	public static void printArr(boolean visited[][]) {
		for (int i = 1; i < visited.length; i++) {
			for (int j = 1; j < visited[0].length; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printArr(int map[][]) {
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void setTopLeftBorder(boolean visited[][]) {
		Coordinate coord = new Coordinate(top.row, top.col);
		while (coord.row != left.row && coord.col != left.col) {
			visited[coord.row][coord.col] = true;

			coord.col--;
			coord.row++;
		}
	}

	public static void setTopRightBorder(boolean visited[][]) {
		Coordinate coord = new Coordinate(top.row, top.col);

		while (coord.row != right.row && coord.col != right.col) {
			visited[coord.row][coord.col] = true;

			coord.col++;
			coord.row++;
		}
	}

	public static void setRightBottomBorder(boolean visited[][]) {
		Coordinate coord = new Coordinate(right.row, right.col);

		while (coord.row != bottom.row && coord.col != bottom.col) {
			visited[coord.row][coord.col] = true;

			coord.col--;
			coord.row++;
		}
	}

	public static void setLeftBottomBorder(boolean visited[][]) {
		Coordinate coord = new Coordinate(left.row, left.col);

		while (coord.row != bottom.row && coord.col != bottom.col) {
			visited[coord.row][coord.col] = true;

			coord.col++;
			coord.row++;
		}
	}

	public static boolean isValid(Coordinate coord, boolean visited[][]) {
		if (coord.row < 1 || coord.col < 1 || coord.row >= visited.length || coord.col >= visited[0].length) {
			return false;
		}
		return true;
	}

	public static boolean isValid(int point[]) {
		if (x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) {
			return true;
		}

		return false;
	}
}

class Coordinate {
	int row;
	int col;

	Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}
