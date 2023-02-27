import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	뱀이 벽이나 자기 자신의 몸에 부딪히면 종료
	이동 규칙
	1. 몸 길이를 늘려(다음 좌표까지) 머리를 목표 지점에 이동(꼬리 고정)
	1.1 사과가 있다면
		사과가 없어지고 꼬리는 움직이지 않음
	1.2 사과가 없다면
		몸길이를 줄여서 머리를 고정하고 꼬리가 움직임
	2. 정해진 시간에 방향을 돌린다(왼쪽,오른쪽), 이때 머리만 방향을 움직인다.
	최초 위치 (1,1) -> 가장 왼쪽 위 좌표
	최초 길이 1
	최초 방향 오른쪽
	매초마다 움직임
	
	현재 몸이 있는 영역을 방문처리하여 몸에 부딪히는지 판정 필요
	좌표, 방향, 길이 정보 객체 필요
	X는 최대 10,000
	초당 움직임을 표현하면 10,000연산이라 가능
*/
public class Main {
	static int upDown[] = { -1, 0, 1, 0 };
	static int leftRight[] = { 0, 1, 0, -1 };
	static int UP = 0;
	static int RIGHT = 1;
	static int DOWN = 2;
	static int LEFT = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.valueOf(br.readLine()); // 보드의 크기
		int map[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		String[] commandList = new String[100001];
		Node head = new Node(new Coordinate(0, 0));
		Node tail = head;
		tail.next = head;

		visited[0][0] = true;

		int K = Integer.valueOf(br.readLine()); // 사과의 개수

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.valueOf(st.nextToken()) - 1;
			int col = Integer.valueOf(st.nextToken()) - 1;

			map[row][col] = 1; // 사과
		}

		int L = Integer.valueOf(br.readLine());
		int time = 0;
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nextTime = Integer.valueOf(st.nextToken());
			String command = st.nextToken();
			commandList[nextTime] = command;
		}
		int direction = RIGHT;

		while (true) {
			time++;

			Coordinate headNextCoord = new Coordinate(head.coord.row + upDown[direction],
					head.coord.col + leftRight[direction]);

			if (headNextCoord.row < 0 || headNextCoord.col < 0 || headNextCoord.row >= map.length
					|| headNextCoord.col >= map[0].length) { // 벽에
				break;
			}
			if (visited[headNextCoord.row][headNextCoord.col]) { // 뱀의 몸와 닿음
				break;
			}

			Node next = new Node(headNextCoord);
			head.next = next;
			head = head.next;

			visited[head.coord.row][head.coord.col] = true;
			if (map[head.coord.row][head.coord.col] == 1) { // 사과가 있다면 사과 없애기
				map[head.coord.row][head.coord.col] = 0;
			} else { // 사과가 없다면 꼬리 좌표를 다음 좌표로
				visited[tail.coord.row][tail.coord.col] = false;
				tail = tail.next;
			}

			// 시간이 됐으면 방향 전환

			if (commandList[time] == null) {
			} else if (commandList[time].equals("D")) {
				direction = direction + 1 > 3 ? 0 : direction + 1;
			} else if (commandList[time].equals("L")) {
				direction = direction - 1 < 0 ? 3 : direction - 1;
			}

		}

		System.out.println(time);
	}

	public static void printArr(boolean map[][]) {
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

class Node {
	Coordinate coord;
	Node next;

	public Node(Coordinate coord) {
		this.coord = coord;
	}

	@Override
	public String toString() {
		return "Node [coord=" + coord;
	}

}
