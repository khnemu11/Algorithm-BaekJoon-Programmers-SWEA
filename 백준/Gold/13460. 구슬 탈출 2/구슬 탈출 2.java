import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char map[][];
	static boolean visited[][][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		int maxCnt = 10;
		int RED = 0;
		int BLUE = 1;
		map = new char[height][width];
		visited = new boolean[height][width][height][width];

		Coordinate red = null;
		Coordinate blue = null;
		Coordinate goal = null;

		for (int i = 0; i < height; i++) {
			char row[] = br.readLine().toCharArray();
			for (int j = 0; j < width; j++) {
				map[i][j] = row[j];

				if (map[i][j] == 'R') {
					map[i][j] = '.';
					red = new Coordinate(i, j);
				}
				if (map[i][j] == 'B') {
					map[i][j] = '.';
					blue = new Coordinate(i, j);
				}
				if (map[i][j] == 'O') {
					goal = new Coordinate(i, j);
				}
			}
		}
		Coordinate[] starts = new Coordinate[2];
		starts[RED] = red;
		starts[BLUE] = blue;

		Queue<Coordinate[]> q = new LinkedList<>();

		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		q.add(starts);
		int cnt = 1;
		boolean isFind = false;

		while (!q.isEmpty()) {
			if (cnt > 10) {
				break;
			}
			int size = q.size();
			while (size-- > 0) {
				Coordinate curr[] = q.poll();
				visited[curr[RED].row][curr[RED].col][curr[BLUE].row][curr[BLUE].col] = true;

				for (int k = 0; k < upDown.length; k++) {
					Coordinate redNext = new Coordinate(curr[RED].row, curr[RED].col);
					Coordinate blueNext = new Coordinate(curr[BLUE].row, curr[BLUE].col);

					boolean redGoal = false;
					boolean blueGoal = false;
					if (compareTo(redNext, blueNext, k) < 0) {
						while (isValid(redNext)) {
							if (map[redNext.row][redNext.col] == 'O') {
								redGoal = true;

								break;
							}
							redNext.row += upDown[k];
							redNext.col += leftRight[k];
						}

						if (!redGoal && (redNext.row != curr[RED].row || redNext.col != curr[RED].col)) {
							redNext.row -= upDown[k];
							redNext.col -= leftRight[k];
						}

						while (isValid(blueNext, redNext)) {

							if (map[blueNext.row][blueNext.col] == 'O') {
								blueGoal = true;
								break;
							}
							blueNext.row += upDown[k];
							blueNext.col += leftRight[k];
						}

						if (map[blueNext.row][blueNext.col] != 'O'
								&& (blueNext.row != curr[BLUE].row || blueNext.col != curr[BLUE].col)) {
							blueNext.row -= upDown[k];
							blueNext.col -= leftRight[k];
						}
					} else {
						while (isValid(blueNext)) {
							if (map[blueNext.row][blueNext.col] == 'O') {
								blueGoal = true;
								break;
							}

							blueNext.row += upDown[k];
							blueNext.col += leftRight[k];
						}

						if (map[blueNext.row][blueNext.col] != 'O'
								&& (blueNext.row != curr[BLUE].row || blueNext.col != curr[BLUE].col)) {
							blueNext.row -= upDown[k];
							blueNext.col -= leftRight[k];
						}
						while (isValid(redNext, blueNext)) {
							if (map[redNext.row][redNext.col] == 'O') {
								redGoal = true;
								break;
							}
							redNext.row += upDown[k];
							redNext.col += leftRight[k];
						}

						if (map[redNext.row][redNext.col] != 'O'
								&& (redNext.row != curr[RED].row || redNext.col != curr[RED].col)) {
							redNext.row -= upDown[k];
							redNext.col -= leftRight[k];
						}

					}
					if (blueGoal || visited[redNext.row][redNext.col][blueNext.row][blueNext.col]) {
						continue;
					}

					if (redGoal == true) {
						isFind = true;
						break;
					}
					visited[redNext.row][redNext.col][blueNext.row][blueNext.col] = true;

					Coordinate[] next = new Coordinate[2];
					next[0] = redNext;
					next[1] = blueNext;

					q.add(next);
				}
			}

			if (isFind) {
				break;
			}
			cnt++;
		}
		if (isFind && cnt <= 10) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}

		bw.newLine();
		bw.flush();
	}

	public static boolean isValid(Coordinate coord) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length
				|| map[coord.row][coord.col] == '#') {
			return false;
		}

		return true;
	}

	public static boolean isValid(Coordinate coord1, Coordinate coord2) {
		if (!isValid(coord1)) {
			return false;
		}
		if (map[coord2.row][coord2.col] == 'O') {
			return true;
		}
		if (coord1.row == coord2.row && coord1.col == coord2.col) {
			return false;
		}
		return true;
	}

	public static int compareTo(Coordinate red, Coordinate blue, int direction) {
		int UP = 0;
		int DOWN = 1;
		int LEFT = 2;

		if (direction == UP) {
			return red.row - blue.row;
		} else if (direction == DOWN) {
			return blue.row - red.row;
		} else if (direction == LEFT) {
			return red.col - blue.col;
		} else {
			return blue.col - red.col;
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
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
}