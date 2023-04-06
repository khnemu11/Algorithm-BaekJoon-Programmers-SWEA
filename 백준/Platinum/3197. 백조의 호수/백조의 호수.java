import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 빙하 녹이기 => 4방향 bfs
 * 
 * 백조와 백조가 만난다 == 백조와 백조는 같은 집합이다 =>유니온 파인드
 * 
 * 
 * */

public class Main {
	static int parents[];
	static char graph[][];
	static boolean areaVisited[][];
	static Queue<Coordinate> lakes = new LinkedList<>();
	static Coordinate swans[] = new Coordinate[2];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1, };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());

		graph = new char[height][width];
		parents = new int[height * width];
		areaVisited = new boolean[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				parents[row * width + col] = row * width + col;
			}
		}

		for (int i = 0; i < height; i++) {
			char row[] = br.readLine().toCharArray();

			for (int j = 0; j < width; j++) {
				graph[i][j] = row[j];

				if (graph[i][j] != 'X') {
					lakes.add(new Coordinate(i, j));
				}
				if (graph[i][j] == 'L') {
					graph[i][j] = '.';
					if (swans[0] == null) {
						swans[0] = new Coordinate(i, j);
					} else {
						swans[1] = new Coordinate(i, j);
					}
				}
			}
		}
		
		for (int row = 0; row < graph.length; row++) {
			for (int col = 0; col < graph[0].length; col++) {
				if (areaVisited[row][col]) {
					continue;
				}
				if (graph[row][col] != '.') {
					continue;
				}
				divideArea(new Coordinate(row, col), new Coordinate(row, col));
			}
		}
//		printArr(graph);
//		for (int i = 0; i < parents.length; i++) {
//			System.out.print(getParent(i) + " ");
//		}
		int time = 0;

		while (true) {
			int swanNo1 = swans[0].row * width + swans[0].col;
			int swanNo2 = swans[1].row * width + swans[1].col;

			if (getParent(swanNo1) == getParent(swanNo2)) {
				break;
			}
//			System.out.println(lakes);
			meltIce();

//			printArr(graph);
//			for (int i = 0; i < parents.length; i++) {
//				System.out.print(getParent(i) + " ");
//			}
			time++;
		}

//		System.out.println(Arrays.toString(swans));
//		for (int i = 0; i < parents.length; i++) {
//			System.out.print(getParent(i) + " ");
//		}
		bw.write(time + "\n");
		bw.flush();
	}

	public static void meltIce() {
		int loop = lakes.size();
//		System.out.println(lakes);
		while (loop-- > 0) {
			Coordinate coord = lakes.poll();
//			System.out.println(coord);

			for (int k = 0; k < upDown.length; k++) {
				Coordinate next = new Coordinate(coord.row + upDown[k], coord.col + leftRight[k]);

				if (isOutOfArray(next, graph)) {
//					System.out.println("배열밖");
					continue;
				}

				int currNo = coord.row * graph[0].length + coord.col;
				int nextNo = next.row * graph[0].length + next.col;

				if (getParent(currNo) == getParent(nextNo)) {
//					System.out.println("방문함");
					continue;
				}

//				System.out.println("union " + currNo + " with " + nextNo);
				graph[next.row][next.col] = '.';
				union(currNo, nextNo);

//				for (int i = 0; i < parents.length; i++) {
//					System.out.print(getParent(i) + " ");
//				}
				for (int j = 0; j < upDown.length; j++) {
					Coordinate nextnext = new Coordinate(next.row + upDown[j], next.col + leftRight[j]);

					if (isOutOfArray(nextnext, graph)) {
//						System.out.println("배열밖");
						continue;
					}
					if (graph[nextnext.row][nextnext.col] != '.') {
						continue;
					}

					int nextNextNo = nextnext.row * graph[0].length + nextnext.col;

					if (getParent(nextNextNo) == getParent(nextNo)) {
//						System.out.println("방문함");
						continue;
					}

//					System.out.println("union " + nextNextNo + " with " + nextNo);
					graph[nextnext.row][nextnext.col] = '.';
					union(nextNextNo, nextNo);

//					for (int i = 0; i < parents.length; i++) {
//						System.out.print(getParent(i) + " ");
//					}

					lakes.add(nextnext);
				}

				lakes.add(next);
			}
		}
	}

	public static void divideArea(Coordinate coord, Coordinate parent) {
		areaVisited[coord.row][coord.col] = true;
//		System.out.println(coord);
		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(coord.row + upDown[k], coord.col + leftRight[k]);

			if (isOutOfArray(next, graph)) {
//				System.out.println("배열밖");
				continue;
			}
			if (areaVisited[next.row][next.col]) {
//				System.out.println("방문함");
				continue;
			}
			if (graph[next.row][next.col] != graph[parent.row][parent.col]) {
//				System.out.println("값이 다름");
				continue;
			}

			int parentNo = parent.row * graph[0].length + parent.col;
			int childNo = next.row * graph[0].length + next.col;
//			System.out.println("union " + parentNo + " with " + childNo);
			union(parentNo, childNo);
			divideArea(next, parent);
		}
	}

	public static boolean isOutOfArray(Coordinate coord, char[][] graph) {
		if (coord.row < 0 || coord.col < 0 || graph.length <= coord.row || graph[0].length <= coord.col) {
			return true;
		}
		return false;
	}

	public static int getParent(int child) {
		if (child == parents[child]) {
			return child;
		}

		parents[child] = getParent(parents[child]);

		return parents[child];
	}

	public static void union(int parent, int child) {
		int pa = getParent(parent);
		int pb = getParent(child);

		parents[pb] = pa;
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