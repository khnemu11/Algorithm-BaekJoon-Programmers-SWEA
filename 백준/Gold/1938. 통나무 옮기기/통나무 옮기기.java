
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * 풀이 알고리즘
 * 
 * bfs로 모든 경우의 수 탐색
 * 이때 가장 먼저 도착한 경우를 구해야 하므로 시간 기준 우선순위 큐를 활용
 * 
 * */

public class Main {
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1, };

	// 방문배열을 기둥의 3가지 좌표의 hashcode로 확인
	static Set<List<Coordinate>> visited = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 보드 사이즈
		int size = Integer.valueOf(br.readLine());
		char map[][] = new char[size][size];

		// 시작 기둥 모임
		WallSet startWallSet = new WallSet();
		// 목표 기둥 모임
		WallSet endWallSet = new WallSet();
		// 입력
		for (int i = 0; i < size; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < map[i].length; j++) {
				// 시작 위치 설정
				if (map[i][j] == 'B') {
					startWallSet.add(new Coordinate(i, j));
					map[i][j] = '0';
				}
				// 목표 위치 설정
				else if (map[i][j] == 'E') {
					endWallSet.add(new Coordinate(i, j));
					map[i][j] = '0';
				}
			}
		}
		// 현재 시간
		int time = 0;
		// 가능한지 판단하는변수
		PriorityQueue<WallSet> pq = new PriorityQueue<>();
		pq.add(startWallSet);

		while (!pq.isEmpty()) {
			WallSet curr = pq.poll();
			// 현재 기둥 모임을 방문 기록
			visited.add(curr.wallList);

			// 목표 위치에 도달했으면 반복문 탈출
			if (curr.equals(endWallSet)) {
				time = curr.time;
				break;
			}
			// 4방향 이동
			for (int i = 0; i < dx.length; i++) {
				WallSet next = new WallSet();
				for (Coordinate coord : curr.wallList) {
					next.add(new Coordinate(coord.row + dx[i], coord.col + dy[i]));
				}
				next.time = curr.time + 1;

				if (!canMove(next, i, map)) {
					continue;
				}

				visited.add(next.wallList);
				pq.add(next);
			}

			// 회전
			if (canSpin(map, curr)) {
				WallSet next = new WallSet();

				// 가로 모양 이라면
				if (curr.wallList.get(0).row == curr.wallList.get(1).row) {
					next.add(new Coordinate(curr.wallList.get(1).row - 1, curr.wallList.get(1).col));
					next.add(new Coordinate(curr.wallList.get(1).row, curr.wallList.get(1).col));
					next.add(new Coordinate(curr.wallList.get(1).row + 1, curr.wallList.get(1).col));
				}
				// 세로 라면
				else {
					next.add(new Coordinate(curr.wallList.get(1).row, curr.wallList.get(1).col - 1));
					next.add(new Coordinate(curr.wallList.get(1).row, curr.wallList.get(1).col));
					next.add(new Coordinate(curr.wallList.get(1).row, curr.wallList.get(1).col + 1));
				}

				next.time = curr.time + 1;
				if (!visited.contains(next.wallList)) {
					visited.add(next.wallList);
					pq.add(next);
				}
			}
		}

		// 도달할 수 있는 경우와 없는 경우를 나누어 출력
		System.out.println(time);
	}

	// 4방향의 움직임이 가능한지 확인하는 메소드
	public static boolean canMove(WallSet wallSet, int direction, char map[][]) {
		// 방문 확인
		if (visited.contains(wallSet.wallList)) {
			return false;
		}
		for (Coordinate coord : wallSet.wallList) {
			// 맵 밖으로 나가는지 확인
			if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) {
				return false;
			}
			// 1이 있는지 확인
			if (map[coord.row][coord.col] == '1') {
				return false;
			}
		}
		return true;
	}

	// 배열을 돌릴 수 있는지 확인하는 메소드
	public static boolean canSpin(char map[][], WallSet wallSet) {
		// 중심 좌표는 항상 2번째에 있으므로 해당 좌표 꺼내기
		Coordinate mid = wallSet.wallList.get(1);
		// 9방향 이동
		int dxNine[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int dyNine[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 0; i < dxNine.length; i++) {
			Coordinate next = new Coordinate(mid.row + dxNine[i], mid.col + dyNine[i]);
			// 맵 밖으로 나가는지 확인
			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				return false;
			}
			// 1이 있는지 확인
			if (map[next.row][next.col] == '1') {
				return false;
			}
		}

		return true;
	}
}

//시간을 오름차순으로 정렬이 가능하며 비교가 가능한 기둥 모임 클래스
class WallSet implements Comparable<WallSet> {
	List<Coordinate> wallList = new ArrayList<>();
	int time;

	public WallSet(List<Coordinate> wallList, int time) {
		this.wallList = wallList;
		this.time = time;
	}

	public WallSet() {
	}

	public void add(Coordinate wall) {
		wallList.add(wall);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WallSet o = (WallSet) obj;

		if (o.wallList.size() != this.wallList.size()) {
			return false;
		}

		for (int i = 0; i < wallList.size(); i++) {
			if (o.wallList.get(i).row != this.wallList.get(i).row
					|| o.wallList.get(i).col != this.wallList.get(i).col) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int compareTo(WallSet o) {
		return this.time - o.time;
	}
}

//좌표 클래스
class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;

	}

	// hashset을 위한 메소드
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	// hashset을 위한 메소드
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}
