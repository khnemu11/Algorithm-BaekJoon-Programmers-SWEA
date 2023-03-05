import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 목표)
 * 인구 이동이 일어나는 총 횟수
 * 
 * 
 * N : 맵의 크기
 * L : 인구 차이의 최솟값
 * R : 인구 차이의 최대값
 * 
 * 인구 이동 : 현재 도시의 인구가 인접한 4방향의 도시의 인구의 차이 diff가 L<= diff <=R을 만족하는 경우 해당 방향의 국경선을 연다(노드가 연결된다.)
 * 인구 이동을 끝나면 해당 도시의 모든 인구더하고 를 도시의 개수만큼 나눈다.
   
  구현 방법) 
   	유니온-파인드를 이용해 인구이동 조건을 만족하는 도시들의 부모를 같게한다.
  	부모가 같은 곳의 인구의 합을 구한 후 부모가 같은 곳의 개수로 나누어 분배한다.
  	인구 이동이 더이상 없을 때 까지 반복하고 횟수를 출력
  
 	걸린시간 : 48분
 */

public class Main {
	static int parents[]; // 부모
	static int map[][]; // 인구가 담긴 배열
	static int populations[]; // 부모의 인구가 담긴 배열
	static int childNum[]; // 부모의 자식의 개수가 담긴 배열
	static int L;
	static int R;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		L = Integer.valueOf(st.nextToken());
		R = Integer.valueOf(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		int time = 0; //인구 이동의 횟수
		
		while (true) {
			parents = new int[N * N];
			populations = new int[N * N];
			childNum = new int[N * N];

			for (int i = 0; i < parents.length; i++) { // 부모 노드 초기화
				parents[i] = i;
			}

			if (!hasPopulationMove()) {	//인구 이동을 할 수 없다면 탈출
				break;
			}

			movePopulation(); //인구 이동
			time++;
		}

		bw.write(time + "\n");
		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void movePopulation() {	//인구를 이동하는 메소드
		for (int i = 0; i < map.length; i++) {	//부모가 같은 도시의 인구를 합치고 해당 개수를 세는 부분
			for (int j = 0; j < map[0].length; j++) {
				int parent = getParent(i * map.length + j);
				populations[parent] += map[i][j];
				childNum[parent]++;
			}
		}
		for (int i = 0; i < map.length; i++) {	//이동한 총 인구를 도시의 개수만큼 나눈 값을 저장하는 부분
			for (int j = 0; j < map[0].length; j++) {
				int parent = getParent(i * map.length + j);
				map[i][j] = populations[parent] / childNum[parent];
			}
		}
	}

	public static boolean hasPopulationMove() {	//인구 이동이 가능한지 4방향 탐색을 통해 조건에 맞는 도시들을 연결하는 메소드
		boolean isValid = false;
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int currCity = (map.length * i + j);	//현재 도시의 인덱스
				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(i + upDown[k], j + leftRight[k]);
					int nextCity = (map.length * next.row + next.col);	//다음 도시의 인덱스

					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {	//맵밖을 넘어서는 경우
						continue;
					}
					if (getParent(nextCity) == getParent(currCity)) { //부모가 같은 경우
						continue;
					}

					int diff = Math.abs(map[next.row][next.col] - map[i][j]); // 인구 차이

					if (L > diff || diff > R) {	//조건에 맞지 않는 경우
						continue;
					}
					isValid = true;	//인구이동을 할 도시가 있음

					union(nextCity, currCity);	//부모 노드 합치기
				}
			}
		}

		return isValid;
	}

	public static int getParent(int child) {	//부모를 리턴하는 메소드
		if (parents[child] == child) {
			return parents[child];
		}
		parents[child] = getParent(parents[child]);

		return parents[child];
	}
	
	public static void union(int a, int b) { //부모 도시의 인덱스가 작은 순서로 병합하는 메소드
		int pa = getParent(a);
		int pb = getParent(b);

		if (pa < pb) {
			parents[pb] = pa;
		} else {
			parents[pa] = pb;
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
