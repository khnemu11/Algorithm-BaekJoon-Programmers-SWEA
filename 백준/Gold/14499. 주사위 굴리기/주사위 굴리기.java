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
    
     위/아래로 구를 시 -> 왼쪽 오른쪽 값은 안바뀜 -> deque를 앞/뒤를 빼서 넣고 왼쪽/오른쪽 deque를 재설정
    왼쪽/오른쪽으로 구를 시 -> 위/아래 값은 안바뀜 -> deque를 왼/오를 빼서 넣고 위/아래deque를 재설정
    
    걸린 시간 : 1시간 9분
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
		
		while (st.hasMoreTokens()) {
			int command = Integer.valueOf(st.nextToken());
			Coordinate next = new Coordinate(coord.row + upDown[command], coord.col + leftRight[command]);

			if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {	//해당 방향으로 갈 수 있는지 확인
				continue;
			}
			rotate(command);

			System.out.println(TOP);

			coord = next;
			numCopy();
		}

		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void rotate(int command) {	// 명령어 대로 회전하는 메소드 (방향)
		if (command == 1 || command == 2) {	//왼쪽, 오른쪽인 경우
			if (command == 1) { // 오른쪽
				topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());	//오른쪽으로 돌면 left가 위로가 top이 되므로 현재 top을 맨 뒤(right)로 넣는다.

			} else if (command == 2) { // 왼쪽
				topLeftBottomRightQ.addFirst(topLeftBottomRightQ.pollLast()); //오른쪽으로 돌면 right가 위로가 top이 되므로 현재 right를 맨 앞(top)으로 넣는다.
			}
			
			//top,left,bottom,right의 값이 재설정되었으므로 최신화
			//이때 front,back은 바뀌지 않았으므로 그대로
			
			TOP = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			LEFT = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			BOTTOM = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			RIGHT = topLeftBottomRightQ.getFirst();
			topLeftBottomRightQ.addLast(topLeftBottomRightQ.pollFirst());
			
			//top과 bottom이 바뀌었으므로 top->front->bottom->back의 deque를 재설정
			topFrontBottomBackQ = new LinkedList<>();
			topFrontBottomBackQ.add(TOP);
			topFrontBottomBackQ.add(FRONT);
			topFrontBottomBackQ.add(BOTTOM);
			topFrontBottomBackQ.add(BACK);
		} else if (command == 3 || command == 4) {	//위, 아래로 도는 경우
			if (command == 3) { // 위쪽
				topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst()); //위쪽으로 돌면 front가 위로가 top이 되므로 현재 top을 맨 뒤(back)로 넣는다.

			} else if (command == 4) { // 아래쪽
				topFrontBottomBackQ.addFirst(topFrontBottomBackQ.pollLast());//아래쪽으로 돌면 back가 위로가 top이 되므로 현재 back를 맨 앞(top)으로 넣는다.
			}
			//top,FRONT,bottom,BACK의 값이 재설정되었으므로 최신화
			//이때 left,right은 바뀌지 않았으므로 그대로
			TOP = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			FRONT = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			BOTTOM = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			BACK = topFrontBottomBackQ.getFirst();
			topFrontBottomBackQ.addLast(topFrontBottomBackQ.pollFirst());
			
			//top과 bottom이 바뀌었으므로 top->left->bottom->right의 deque를 재설정
			topLeftBottomRightQ = new LinkedList<>();
			topLeftBottomRightQ.add(TOP);
			topLeftBottomRightQ.add(LEFT);
			topLeftBottomRightQ.add(BOTTOM);
			topLeftBottomRightQ.add(RIGHT);
		}

	}

	public static void numCopy() {	//맵의 바닥의 숫자처리를 하는 메소드
		if (map[coord.row][coord.col] == 0) {	//맵의 값이 0인경우 주사위의 바닥값을 저장
			map[coord.row][coord.col] = BOTTOM;
		} else {	//맵의 값이 0이 아닌경우
			BOTTOM = map[coord.row][coord.col]; //주사위의 바닥 값을 재설정
			//topLeftBottomRightQ의 바닥인 bottom 은 뒤에서 2번째에 있으므로 해당 값을 재설정
			int right = topLeftBottomRightQ.pollLast();
			topLeftBottomRightQ.pollLast();
			topLeftBottomRightQ.addLast(BOTTOM);
			topLeftBottomRightQ.addLast(right);
			
			//topFrontBottomBackQ의 바닥인 bottom 은 뒤에서 2번째에 있으므로 해당 값을 재설정
			int back = topFrontBottomBackQ.pollLast();
			topFrontBottomBackQ.pollLast();
			topFrontBottomBackQ.addLast(BOTTOM);
			topFrontBottomBackQ.addLast(back);

			map[coord.row][coord.col] = 0; //바닥의 숫자 제거
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
