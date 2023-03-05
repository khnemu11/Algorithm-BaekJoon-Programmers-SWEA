import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 초기 주사위의 모든 면은 0
     출력 : 주사위의 윗면	
     바닥이 0 인 경우 : 주사위의 아랫면을 맵에 저장
     바닥이 0이 아닌 경우 :맵의 값을 주사위의 아랫면에 저장
   
    위/아래로 구를 시 : 위->뒤->아래->앞/위->앞->아래->뒤
    왼쪽/오른쪽으로 구를 수 : 위->왼쪽->아래->오른쪽 / 위->오른족->아래->왼쪽
  deque 2개로 주사위를 구현
    바닥(아래)의 값을 바꾸려고 할 시 뒤쪽을 2번뺀 후 바닥 값을 넣 고 맨 뒤에있던 값을 넣는다.
    위,아래,왼쪽,오른쪽,앞,뒤 
    
     위/아래로 구를 시 -> 왼쪽 오른쪽 값은 안바뀜 -> deque를 앞/뒤를 빼서 넣고 왼쪽/오른쪽 deque를 재설정
    왼쪽/오른쪽으로 구를 시 -> 위/아래 값은 안바뀜 -> deque를 왼/오를 빼서 넣고 위/아래deque를 재설정
 */

public class Main {
	static Deque<Integer> topFrontBottomBackQ = new LinkedList<>();
	static Deque<Integer> topLeftBottomRightQ = new LinkedList<>();
	static Coordinate coord;
	static int map[][];
	static int TOP;
	static int BOTTOM;
	static int LEFT;
	static int RIGHT;
	static int FRONT;
	static int BACK;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int startRow = Integer.valueOf(st.nextToken());
		int startCol = Integer.valueOf(st.nextToken());
		int commmandNum = Integer.valueOf(st.nextToken());

		map = new int[N][M];
		coord = new Coordinate(startRow, startCol);

		for (int i = 0; i < 4; i++) {
			topFrontBottomBackQ.add(0);
			topLeftBottomRightQ.add(0);
		}

		int upDown[] = { 0, 0, 0, -1, 1 };
		int leftRight[] = { 0, 1, -1, 0, 0 };

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
//		System.out.println("TOP : " + TOP + " BOTTOM : " + BOTTOM + " LEFT : " + LEFT + " RIGHT : " + RIGHT
//				+ " FRONT : " + FRONT + " BACK : " + BACK);
//		int idx = 1;
		while (st.hasMoreTokens()) {
//			System.out.println("#" + (idx++));
			int command = Integer.valueOf(st.nextToken());
			Coordinate next = new Coordinate(coord.row + upDown[command], coord.col + leftRight[command]);

			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
				continue;
			}
			rotate(command);

			System.out.println(TOP);

			coord = next;
			numCopy();
//			System.out.println("TOP : " + TOP + " BOTTOM : " + BOTTOM + " LEFT : " + LEFT + " RIGHT : " + RIGHT
//					+ " FRONT : " + FRONT + " BACK : " + BACK);
//			printArr();
//			System.out.println("topLeftBottomRightQ " + topLeftBottomRightQ);
//			System.out.println("topFrontBottomBackQ " + topFrontBottomBackQ);
//			System.out.println();
		}

		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void printArr() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void rotate(int command) {
		if (command == 1 || command == 2) {
			if (command == 1) { // 오른쪽
				topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());

			} else if (command == 2) { // 왼쪽
				topLeftBottomRightQ.addFirst(topLeftBottomRightQ.pollLast());
			}

			TOP = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			LEFT = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			BOTTOM = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			RIGHT = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());

			topFrontBottomBackQ = new LinkedList<>();
			topFrontBottomBackQ.add(TOP);
			topFrontBottomBackQ.add(FRONT);
			topFrontBottomBackQ.add(BOTTOM);
			topFrontBottomBackQ.add(BACK);
		} else if (command == 3 || command == 4) {
			if (command == 3) { // 위쪽
				topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());

			} else if (command == 4) { // 아래쪽
				topFrontBottomBackQ.addFirst(topFrontBottomBackQ.pollLast());
			}
//			System.out.println(topFrontBottomBackQ);
			TOP = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			FRONT = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			BOTTOM = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			BACK = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());

//			System.out.println(topFrontBottomBackQ);

			topLeftBottomRightQ = new LinkedList<>();
			topLeftBottomRightQ.add(TOP);
			topLeftBottomRightQ.add(LEFT);
			topLeftBottomRightQ.add(BOTTOM);
			topLeftBottomRightQ.add(RIGHT);
		}

	}

	public static void numCopy() {
		if (map[coord.row][coord.col] == 0) {
			map[coord.row][coord.col] = BOTTOM;
		} else {
			BOTTOM = map[coord.row][coord.col];

			int right = topLeftBottomRightQ.pollLast();
			topLeftBottomRightQ.pollLast();
			topLeftBottomRightQ.addLast(BOTTOM);
			topLeftBottomRightQ.addLast(right);

			int back = topFrontBottomBackQ.pollLast();
			topFrontBottomBackQ.pollLast();
			topFrontBottomBackQ.addLast(BOTTOM);
			topFrontBottomBackQ.addLast(back);

			map[coord.row][coord.col] = 0;
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
}