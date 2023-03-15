import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	0인 영역의 인접 총 개수를 각 0의 영역마다 구하기
	1인 영역 4방향의 서로 다른 0의 영역의 개수의 합 구하기
 */
public class Main {
	static int areas[];
	static int upDown[] = { 1, -1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static boolean visited[][];
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());

		map = new int[height][width];
		int result[][] = new int[height][width];
		visited = new boolean[height][width];
		areas = new int[height * width + 2];

		Queue<Coordinate> startQueue = new LinkedList<>();
		Queue<Coordinate> zeroQueue = new LinkedList<>();

		for (int i = 0; i < map.length; i++) {
			int nums[] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = nums[j];

				if (map[i][j] == 1) {
					startQueue.add(new Coordinate(i, j));
				} else {
					zeroQueue.add(new Coordinate(i, j));
				}
			}
		}

		int seq = 2; // 1이 벽이므로 2부터 영역 고유값 시작

		while (!zeroQueue.isEmpty()) { // 인접 0인 영역 합치기
			Coordinate curr = zeroQueue.poll();

			if (visited[curr.row][curr.col]) {
				continue;
			}

			unionAdjacentArea(seq, curr);

			seq++;
		}

		setAreaCount(); // 같은 영역의 개수 구하기

		while (!startQueue.isEmpty()) { // 1인 영역의 이동할 수 있는 칸의 개수 구하기
			Coordinate curr = startQueue.poll();

			result[curr.row][curr.col] = 1;
			HashSet<Integer> areaSet = new HashSet<>();

			for (int k = 0; k < 4; k++) {
				Coordinate nextCoordinate = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

				if (outOfArea(map, nextCoordinate) || map[nextCoordinate.row][nextCoordinate.col] == 1) {
					continue;
				}

				areaSet.add(map[nextCoordinate.row][nextCoordinate.col]);
			}

			for (int areaSeq : areaSet) { // 각 영역의 인덱스로 영역의 개수 더하기
				result[curr.row][curr.col] += areas[areaSeq];
			}

			result[curr.row][curr.col] = result[curr.row][curr.col] % 10;
		}

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				bw.write(result[i][j] + "");
			}
			bw.newLine();
		}

		bw.flush();
	}

	public static void printArr(int map[][]) { // 배열 출력 메소드 (맵)
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void setAreaCount() { // 배열의 인덱스로 영역의 개수를 구하는 메소드
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				areas[map[i][j]]++;
			}
		}
	}

	public static void unionAdjacentArea(int seq, Coordinate coord) { // 인접 0인 영역을 합치는 메소드
		map[coord.row][coord.col] = seq;

		Queue<Coordinate> queue = new LinkedList<>();
		queue.add(coord);

		while (!queue.isEmpty()) {
			Coordinate currCoordinate = queue.poll();
			visited[currCoordinate.row][currCoordinate.col] = true;
			map[currCoordinate.row][currCoordinate.col] = seq;

			for (int k = 0; k < 4; k++) {
				Coordinate nextCoordinate = new Coordinate(currCoordinate.row + upDown[k],
						currCoordinate.col + leftRight[k]);
				if (outOfArea(map, nextCoordinate) || visited[nextCoordinate.row][nextCoordinate.col]
						|| map[nextCoordinate.row][nextCoordinate.col] == 1) {
					continue;
				}

				visited[nextCoordinate.row][nextCoordinate.col] = true;
				queue.add(nextCoordinate);
			}
		}
	}

	public static boolean outOfArea(int map[][], Coordinate coord) { // 배열을 벗어나는 좌표인지 확인하는 메소드
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) {
			return true;
		}

		return false;
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