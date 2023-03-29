import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 
 * 	풀이 알고리즘
 * 	
 * 	목표) 원숭이가 목표 좌표로 갈 수 있는 "최소시간"
 * 
 *  조건)
 *  1. 원숭이의 초기 좌표 (0,0), 목표 좌표 (H-1,W-1)
 *  2. 기본적으로 4방향으로 1칸씩 움직임
 * 	      최대 K번까지 체스 말(horse)처럼 움직일 수 있음
 *     이때 말 움직임은 벽을 넘을 수 있다.
 *  3. H<=200, W<=200, K<30
 *  4. 원숭이는 벽에 도달할 수 없다.
 *  
 *  해결 과정)
 *  목표 좌표로 간다=> 그래프 탐색,완전탐색
 *  최소시간 => bfs
 *  
 *  1. 시간 복잡도
 *  bfs의  시간 복잡도 = 노드의 개수 * 간선의 개수
 *  
 *  위의 시간복잡도가 가능한 이유는 각 노드는 한번 방문한다는 가정이 있기 때문
 *  => 한번만 방문하도록 방문처리 필요
 *  => 노드의 개수!= 맵의 크기, 노드의 개수 == 방문 배열의 크기
 *  
 *  방문처리)
 *  1) 방문배열의 크기
 *  방문 배열 = 해당 좌표로 움직일 수 있는가
 *  방문 배열 == [행의 크기][열의 크기], [행의 크기][열의 크기] != 방문 배열
 *  
 *  ex1) 일반 4방향 완전탐색
 *  움직일 수 있는 조건)
 *  1. 해당 좌표를 이전에 이미 도착 했는가
 *  
 *  방문 배열 = [행의 크기][열의 크기]
 *  
 *  ex2) 조건이 있는  4방향 완전탐색
 *  예제 : 벽을 부수고 이동하기
 *  https://www.acmicpc.net/problem/2206
 *  
 *  움직일 수 있는 조건)
 *  1. 해당 좌표를 이전에 이미 도착 했는가
 *  2. 내가 벽을 이전에 부수었는가
 *  
 *  방문 배열 = [2(벽을 부쉈다 or 안부쉈다)][행의 크기][열의 크기]
 *  
 *  ex3) 말이 되고 싶은 원숭이
 *  움직일 수 있는 조건)
 *  1. 해당 좌표를 이전에 이미 도착했는가
 *  2. 내가 이전에 몇번 말의 움직임을 했는가
 *  
 *  방문배열 = [말 움직임을 할 수 있는 최대 횟수][행의크기][열의크기]
 *  
 *  2) boolean vs int
 *  
 *  1.단순히 방문을 한다 -> boolean형 방문 처리
 *  목표 좌표를 이미 도착했다면(true) 더이상 방문하지 않음
 *  
 *  2.방문한 시간이 중요하다 -> int형 방문처리 == dp
 *	ex) 최소시간	
 *	목표 좌표의 시간이 현재 시간보다 크다
 *  =>해당 시간을 현재 시간으로 변경 후 방문   
 *  목표 좌표의 시간이 현재 시간보다 같거나 작다
 *  =>더이상 탐색하지 않음
 *  
 *  원숭이 문제는 시간이 중요하기 때문에 int형 방문처리 실행
 *  
 *  방문 배열 = int visited [말 움직임을 할 수 있는 최대 횟수][행의크기][열의크기];
 *  
 *  3) 최종 시간 복잡도
 *  
 *  노드의 개수 = 방문 배열의 크기 = 가로 * 세로 * 최대 말 움직임 = 200*200*30;
 *  간선 = 4방향 + 말의 움직임 = 4+8 = 12
 *  O(n) = 200*200*30*12 = 14,400,000 < 1억 
 *  => 제한 시간내에 가능
 *  
 *  
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine()); // 말의 움직임의 최대 횟수

		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.valueOf(st.nextToken()); // 너비
		int height = Integer.valueOf(st.nextToken()); // 높이

		int map[][] = new int[height][width];
		int time[][][] = new int[N + 1][height][width]; // [말 움직임을 한 횟수][행의 크기][열의 크기]

		for (int i = 0; i < height; i++) { // 맵 초기화
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < height; j++) {
				Arrays.fill(time[i][j], Integer.MAX_VALUE); // 모든 시간 배열의 값을 최대값으로 초기화
			}
		}

		PriorityQueue<Monkey> pq = new PriorityQueue<>(); // depth가 작은 순서로 정렬한 bfs 우선순위 큐
		pq.add(new Monkey(0, 0, 0, 0)); // 시작 위치의 원숭이 추가

		int cnt = -1; // 경로 초기값

		int rowMove[] = { -1, 1, 0, 0 }; // 4방향 벡터
		int colMove[] = { 0, 0, -1, 1 };

		int horseRowMove[] = { -2, -2, -1, -1, 1, 1, 2, 2 }; // 말의 움직임 =8방향 백터
		int horseColMove[] = { -1, 1, -2, 2, -2, 2, -1, 1 };

		while (!pq.isEmpty()) {// bfs 시작
			Monkey curr = pq.poll();
			if (curr.row == map.length - 1 && curr.col == map[0].length - 1) { // 목표 위치에 도착하면 반복문 탈출
				cnt = curr.cnt;
				break;
			}
			for (int k = 0; k < rowMove.length; k++) { // 4방향 이동
				Monkey next = new Monkey(curr.row + rowMove[k], curr.col + colMove[k], curr.horseMoveCnt, curr.cnt + 1);
				if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) { // 맵을 벗어 나는가
					continue;
				}
				if (map[next.row][next.col] == 1) {// 벽인가
					continue;
				}
				if (time[next.horseMoveCnt][next.row][next.col] > next.cnt) { // 현재 시간을 활용하는게 더 빠른가
					time[next.horseMoveCnt][next.row][next.col] = next.cnt;
					pq.add(next);
				}
			}
			if (curr.horseMoveCnt < N) { // 말 움직임이 가능하면 카운트 하나를 늘리고 말 8방향 움직임
				for (int k = 0; k < horseRowMove.length; k++) {
					Monkey next = new Monkey(curr.row + horseRowMove[k], curr.col + horseColMove[k],
							curr.horseMoveCnt + 1, curr.cnt + 1);
					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
						continue;
					}
					if (map[next.row][next.col] == 1) {
						continue;
					}
					if (time[next.horseMoveCnt][next.row][next.col] > next.cnt) {
						time[next.horseMoveCnt][next.row][next.col] = next.cnt;
						pq.add(next);
					}
				}
			}
		}
		bw.write(cnt + "\n");
		bw.flush();
	}
}

class Monkey implements Comparable<Monkey> { // 원숭이 객체
	int row; // 행
	int col; // 열
	int horseMoveCnt; // 사용한 말의 움직임의 횟수
	int cnt; // 현재 depth

	public Monkey(int row, int col, int horseMoveCnt, int cnt) {
		this.row = row;
		this.col = col;
		this.horseMoveCnt = horseMoveCnt;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Monkey o) {
		return this.cnt - o.cnt;
	}
}
