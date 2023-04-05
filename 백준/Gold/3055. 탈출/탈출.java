import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 1) 물 이동 좌표 구해서 해당 시간 입력
 * 2) 물 이동 좌표의 시간이 고슴도치 시간보다 작을 경우 이동
 * 
 * */

public class Main {
	static Queue<Coordinate> water = new LinkedList<>();
	static String map[][];
	static boolean waterVisited[][];
	static boolean hedgehogVisited[][];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());

		waterVisited = new boolean[height][width];
		hedgehogVisited = new boolean[height][width];
		map = new String[height][width];
		Coordinate cave = null;
		Animal hedgehog = null;

		for (int row = 0; row < height; row++) {
			String split[] = br.readLine().split("");
			for (int col = 0; col < width; col++) {
				map[row][col] = split[col];
				if (map[row][col].equals("D")) {
					cave = new Coordinate(row, col);
				} else if (map[row][col].equals("S")) {
					hedgehog = new Animal(row, col);
				} else if (map[row][col].equals("*")) {
					water.add(new Coordinate(row, col));
				}
			}
		}

		Queue<Animal> candidates = new LinkedList<>();
		candidates.add(hedgehog);

		int minTime = -1;

		while (!candidates.isEmpty()) {
			int loop = candidates.size();
			moveWater();
			while (loop-- > 0) {
				Animal curr = candidates.poll();
				hedgehogVisited[curr.row][curr.col] = true;
				map[curr.row][curr.col] = ".";

				if (curr.row == cave.row && curr.col == cave.col) {
					minTime = curr.time;
					break;
				}

				for (int k = 0; k < upDown.length; k++) {
					Animal next = new Animal(curr.row + upDown[k], curr.col + leftRight[k], curr.time + 1);

					if (isOutOfArray(next, map)) { // 고슴도치가 맵 밖으로 넘어갈 때
						continue;
					} else if (map[next.row][next.col].equals("X")) { // 다음 위치가 바위인 경우
						continue;
					} else if (hedgehogVisited[next.row][next.col]) { // 이전에 고슴도치가 도착한 곳이 더 빨리 도착했을 때
						continue;
					} else if (waterVisited[next.row][next.col]) { // 이전에 고슴도치가 도착한 곳이 더 빨리 도착했을 때
						continue;
					}
					map[next.row][next.col] = "S";
					hedgehogVisited[next.row][next.col] = true;
					candidates.add(next);
				}
			}
			if (minTime != -1) {
				break;
			}

		}

		if (minTime == -1) {
			bw.write("KAKTUS\n");
		} else {
			bw.write(minTime + "\n");
		}

		bw.flush();
	}

	public static void printArr() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void moveWater() {
		int size = water.size();

		while (size-- > 0) {
			Coordinate curr = water.poll();
			waterVisited[curr.row][curr.col] = true;
			map[curr.row][curr.col] = "*";
			for (int k = 0; k < upDown.length; k++) {
				Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);
				if (isOutOfArray(next, map)) { // 물이 맵 밖으로 넘어갈 때
					continue;
				} else if (map[next.row][next.col].equals("X")) { // 다음 위치가 바위인 경우
					continue;
				} else if (waterVisited[next.row][next.col]) { // 이전에 이미 물바다가 되었을 때
					continue;
				} else if (map[next.row][next.col].equals("D")) { // 동굴인 경우
					continue;
				}

				map[next.row][next.col] = "*";
				waterVisited[next.row][next.col] = true;
				water.add(next);
			}
		}
	}

	public static boolean isOutOfArray(Coordinate coord, Object[][] map) {
		if (map.length <= coord.row || map[0].length <= coord.col || coord.row < 0 || coord.col < 0) {
			return true;
		}
		return false;
	}

}

class Animal extends Coordinate {
	int time;

	public Animal(int row, int col) {
		super(row, col);
	}

	public Animal(int row, int col, int time) {
		super(row, col);
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

	public String toString() {
		return row + " , " + col;
	}
}