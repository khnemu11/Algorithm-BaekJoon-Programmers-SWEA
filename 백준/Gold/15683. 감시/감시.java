import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 
 * 각 cctv의 방향대로 구현만 하면 되는 문제
 * 
 * 이때 각 cctv마다 볼 수 있는 방향의 개수가 다르므로 각 방향에 따른 조합이 필요
 * 
 * 1,3,4의 경우 90씩 회전하면 총 4가지의 경우의 수
 * 2의 경우 2가지의 경우의수
 * 5의 경우 1가지의 경우의수
 * -> cctv마다 경우의 수가 다르므로 회전 가능 횟수를 저장할 필요가 있음
 * 
 * 모든 조합을 따져야 하므로 dfs사용
 * */
public class Main {
	static int map[][]; // cctv맵
	static int visited[][]; // 각 영역을 어떤 cctv가 감시하고 있는지 정보가 담겨있는 방문배열
	static int UP = 0;
	static int RIGHT = 1;
	static int DOWN = 2;
	static int LEFT = 3;
	static ArrayList<CCTV> cctvList; // cctv 리스트
	static int minBlindSpot; // 최소 사각 지역의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		map = new int[height][width];
		visited = new int[height][width];
		cctvList = new ArrayList<>();
		int initBlindSpotNum = 0; // 최초 사각 지역의 개수

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				visited[i][j] = -1; // -1 : 방문하지 않음 n : n번째 cctv가 방문함
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] > 0 && map[i][j] < 6) { // cctv이면 cctv 객체 추가
					CCTV cctv = new CCTV(i, j, map[i][j]);
					cctvList.add(cctv);
				} else if (map[i][j] == 0) { // 사각 지역이면 초기 사각지역 추가
					initBlindSpotNum++;
				}
			}
		}
		minBlindSpot = initBlindSpotNum;

		findMinBlindSpot(0);	//사각 지역 최소 개수 탐색
		bw.write(minBlindSpot + "\n");
		bw.flush();
		bw.close();
	}

	public static void findMinBlindSpot(int cctvIdx) {	//사각지역을 dfs로 탐색하는 메소드 (현재 cctv의 인덱스)
		if (cctvIdx == cctvList.size()) {
			int blindSpotCnt = 0;

			for (int i = 0; i < visited.length; i++) {	//방문 배열을 통해 방문하지 않으면서 사각 지역인 개수 파악
				for (int j = 0; j < visited[0].length; j++) {
					if (visited[i][j] == -1 && map[i][j] == 0) {
						blindSpotCnt++;
					}
				}
			}

			minBlindSpot = Math.min(blindSpotCnt, minBlindSpot);	//최소 개수 최신화
		} else {
			CCTV cctv = cctvList.get(cctvIdx);

			for (int i = 0; i < cctv.rotateCnt; i++) {	//회전 가능한 횟수만큼 cctv 감시 및 회전
				cctv.on(map, visited, cctvIdx);	//감시 시작
				findMinBlindSpot(cctvIdx + 1);	//다음 cctv 탐색
				cctv.off(map, visited, cctvIdx); //감시 해제
				cctv.rotate();	//cctv 회전
			}
		}
	}
}

class Coordinate {	//좌표
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class CCTV extends Coordinate {	
	int type;	//cctv 종류
	int rotateCnt;	//회전 가능 횟수
	ArrayList<Integer> directionList; //방향 리스트

	public CCTV(int row, int col, int type) {
		super(row, col);
		this.row = row;
		this.col = col;
		this.type = type;
		directionList = new ArrayList<Integer>();

		if (type == 1) {	//1번인 경우 위부터 시작
			directionList.add(Main.UP);
			this.rotateCnt = 4;
		} else if (type == 2) {	//2번인 경우 왼쪽,오른쪽 시작
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 2;
		} else if (type == 3) {	//3번인 경우 위,오른쪽 시작
			directionList.add(Main.UP);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 4;
		} else if (type == 4) {	//4번인 경우 위, 왼쪽,오른쪽 시작
			directionList.add(Main.UP);
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			this.rotateCnt = 4;
		} else if (type == 5) {	//5번인 경우 모든 방향 시작
			directionList.add(Main.UP);
			directionList.add(Main.LEFT);
			directionList.add(Main.RIGHT);
			directionList.add(Main.DOWN);
			this.rotateCnt = 1;
		}
	}

	public void on(int map[][], int visited[][], int cctvIdx) { // 감시를 시작하는 메소드 (지역 정보, 방문 정보, 현재 cctv 인덱스)
		int upDown[] = { -1, 0, 1, 0 };	//위,오른쪽,아래, 왼쪽 4방향 백터
		int leftRight[] = { 0, 1, 0, -1 };	

		for (int direction : directionList) {	//방향의 개수만큼 감시
			Coordinate curr = new Coordinate(this.row + upDown[direction], this.col + leftRight[direction]);	//cctv 다음 좌표부터 감시

			while (Validator.isValid(curr, map)) {	//감시 할 수 있는 영역이면 감시
				if (map[curr.row][curr.col] == 0 && visited[curr.row][curr.col] == -1) { // 해당 영역이 감시영역이고 아직 아무도 감시하지않는다면 현재 cctv로 감시
					visited[curr.row][curr.col] = cctvIdx;
				}

				curr.row += upDown[direction];
				curr.col += leftRight[direction];
			}
		}
	}

	public void off(int map[][], int visited[][], int cctvIdx) {// 감시를 해제하는 메소드 (지역 정보, 방문 정보, 현재 cctv 인덱스)
		int upDown[] = { -1, 0, 1, 0 };
		int leftRight[] = { 0, 1, 0, -1 };

		for (int direction : directionList) {
			Coordinate curr = new Coordinate(this.row + upDown[direction], this.col + leftRight[direction]);

			while (Validator.isValid(curr, map)) {
				if (visited[curr.row][curr.col] == cctvIdx) { // 현재 cctv가 감시영역을 감시중이라면 감시 해제
					visited[curr.row][curr.col] = -1;
				}

				curr.row += upDown[direction];
				curr.col += leftRight[direction];
			}
		}
	}

	public void rotate() { // 방향을 돌리는 메소드
		for (int i = 0; i < directionList.size(); i++) {
			directionList.set(i, directionList.get(i) + 1 > Main.LEFT ? 0 : directionList.get(i) + 1); // 위(0)->오른쪽(1)->아래(2)->왼쪽(3)->위(0) 순서가 되도록 방향 조정
		}
	}
}

class Validator {	//가능한 좌표인지 판단하는 validator
	public static boolean isValid(Coordinate coord, int map[][]) {	//감시할 수 있는지 판단하는 메소드 (현재 좌표, 영역)
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) { // 맵을 벗어나는 경우
			return false;
		}

		if (map[coord.row][coord.col] == 6) { // 벽인경우
			return false;
		}
		return true;
	}
}