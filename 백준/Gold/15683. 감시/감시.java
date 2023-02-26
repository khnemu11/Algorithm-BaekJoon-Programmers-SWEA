import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * */
public class Main {
	static int map[][];
	static int visited[][];
	static int UP = 0;
	static int RIGHT = 1;
	static int DOWN = 2;
	static int LEFT = 3;
	static ArrayList<CCTV> cctvList;
	static int minBlindSpot;
	static int initBlindSpotNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		map = new int[height][width];
		visited = new int[height][width];
		cctvList = new ArrayList<>();

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				visited[i][j] = -1;
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] > 0 && map[i][j] < 6) {
					CCTV cctv = new CCTV(i, j, map[i][j]);
					cctvList.add(cctv);
				} else if (map[i][j] == 0) {
					initBlindSpotNum++;
				}
			}
		}
		minBlindSpot = initBlindSpotNum;

//		System.out.println("cctv 리스트 : " + cctvList);
		findMinBlindSpot(0);
		bw.write(minBlindSpot + "\n");
		bw.flush();
		bw.close();
	}

	public static void findMinBlindSpot(int cctvIdx) {
		if (cctvIdx == cctvList.size()) {

			int blindSpotCnt = 0;

			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[0].length; j++) {
					if (visited[i][j] == -1 && map[i][j] == 0) {
						blindSpotCnt++;
					}
				}
			}

//			System.out.println("총 감시 지역 개수 : " + blindSpotCnt + " vs 최소" + minBlindSpot);
//			printArr(visited);
			minBlindSpot = Math.min(blindSpotCnt, minBlindSpot);
		} else {
			CCTV cctv = cctvList.get(cctvIdx);

			for (int i = 0; i < cctv.rotateCnt; i++) {
				int surveilanceSpotCnt = cctv.on(map, visited, cctvIdx);
//				System.out.println("감시 가능한 지역 개수 : " + surveilanceSpotCnt);
//				printArr(visited);
				findMinBlindSpot(cctvIdx + 1);
				cctv.off(map, visited, cctvIdx);
				cctv.rotate();
			}
		}
	}

	public static void printArr(int visited[][]) {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				System.out.print(visited[i][j] + " ");
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
}

class CCTV extends Coordinate {
	int type;
	int rotateCnt;
	ArrayList<Integer> directionList;

	public CCTV(int row, int col, int type) {
		super(row, col);
		this.row = row;
		this.col = col;
		this.type = type;
		directionList = new ArrayList<Integer>();

		if (type == 1) {
			directionList.add(Main.UP);
			this.rotateCnt = 4;
		} else if (type == 2) {
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 2;
		} else if (type == 3) {
			directionList.add(Main.UP);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 4;
		} else if (type == 4) {
			directionList.add(Main.UP);
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 4;
		} else if (type == 5) {
			directionList.add(Main.UP);
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			directionList.add(Main.DOWN);
			this.rotateCnt = 1;
		}
	}

	public int on(int map[][], int visited[][], int cctvIdx) { // 감시를 시작하는 메소드
		int upDown[] = { -1, 0, 1, 0 };
		int leftRight[] = { 0, 1, 0, -1 };
		int surveilanceSpotCnt = 0;

		for (int direction : directionList) {
			Coordinate curr = new Coordinate(this.row + upDown[direction], this.col + leftRight[direction]);

			while (Validator.canWatch(curr, map)) {
				if (map[curr.row][curr.col] == 0 && visited[curr.row][curr.col] == -1) { // 해당 영역이 감시영역이고 아직 아무도 감시하지
																							// 않는다면
					visited[curr.row][curr.col] = cctvIdx;
					surveilanceSpotCnt++;
				}

				curr.row += upDown[direction];
				curr.col += leftRight[direction];
			}
		}

		return surveilanceSpotCnt;
	}

	public int off(int map[][], int visited[][], int cctvIdx) {
		int upDown[] = { -1, 0, 1, 0 };
		int leftRight[] = { 0, 1, 0, -1 };
		int blindSpot = 0;

		for (int direction : directionList) {
			Coordinate curr = new Coordinate(this.row + upDown[direction], this.col + leftRight[direction]);

			while (Validator.canOff(curr, map)) {
				if (map[curr.row][curr.col] == 0 && visited[curr.row][curr.col] == cctvIdx) {
					blindSpot++; // 감시 대상 영역이면 감시 영역 개수 증가
				}

				if (visited[curr.row][curr.col] == cctvIdx) { // 현재 cctv가 감시영역을 감시중이라면 감시 해제
					visited[curr.row][curr.col] = -1;
				}

				curr.row += upDown[direction];
				curr.col += leftRight[direction];
			}
		}

		return blindSpot;
	}

	public void rotate() { // 방향을 돌리는 메소드
		for (int i = 0; i < directionList.size(); i++) {
			directionList.set(i, directionList.get(i) + 1 > Main.LEFT ? 0 : directionList.get(i) + 1); // 왼쪽(3)에서 위(0)로
		}
	}

	@Override
	public String toString() {
		return "CCTV [row=" + row + ", col= " + col + ", type=" + type + ", rotateCnt=" + rotateCnt + ", directionList="
				+ directionList + "]";
	}
}

class Validator {
	public static boolean canWatch(Coordinate coord, int map[][]) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) { // 맵을 벗어나는 경우
			return false;
		}

		if (map[coord.row][coord.col] == 6) { // 벽인경우
			return false;
		}
		return true;
	}

	public static boolean canOff(Coordinate coord, int map[][]) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) { // 맵을 벗어나는 경우
			return false;
		}

		if (map[coord.row][coord.col] == 6) { // 벽인경우
			return false;
		}
		return true;
	}
}