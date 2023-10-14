/*
    포탑의 공격력은 변화가능
    0이 되면 더이상 공격 X

    시나리오

    1. 공격력이 가장 약한 포탑이 공격자로 선정
    우선순위)
    공격력이 낮음 > 가장 최근에 공격함 > 행+열의 합이 가장 큼 > 열의 값이 가장 큼

    2. 선정된 포탑의 공격력이 맵의 행의 크기 * 맵의 열의 크기 만큼 증가

    3. 가장 강한 포탑을 공격
    우선순위)
    공격력이 높음 > 가장 나중에 공격함 > 행+열의 합이 가장 작음 > 열의 값이 가장 작음

    4. 레이저 공격
    이동 규칙)
    1. 상하좌우
    2. 부서진 포탑으 이동 X
    3. 맨 끝으로 도달하면 처음으로
    4. 목표 대상에 도달하면 목표 대상에 공격자의 공격력 만큼 데미지
    4.1 경로에 존재하는 포탑에는 공격자의 공격력의 절반 데미지
    4.2 도달하지 못하면 레이저 공격 실패

    5. 포탄 공격
    1. 공격 대상에 포탄을 8방향공격
    2. 공격자는 피해 X
    3. 가장자리인 경우 반대쪽에 피해
    4. 중앙에는 공격 포탑의 공격력만큼 피해
    5. 나머지는 절반의 피해

    6. 공격력 0이하면 부서짐
    7. 공격력이 0 이하가 아니며 공격과 무관한(공격자X, 피해자 X) 포탑의 공격력을 1 증가

    가장 강한 포탑의 공격력 출력
*/

import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int[][] attackTurn;
	static boolean[][] visited;
	static int[][] times;
	static List<Coordinate> minRoute;
	static int N,M,K;

	public static void main(String[] args) throws Exception{
		//입력값 넣기
		init();

		//포탑 부수기 시작
		for(int round=1;round<=K;round++) {
			//모든 포탑 탐색
			List<Turret> turretList = findAllTurrets();

			//부서지지 않은 포탑이 1개라면 라운드 종료
			if(turretList.size() == 1) {
				break;
			}

			//가장 약한/강한 포탑을 선택하기 위해 포탑 목록 정렬
			Collections.sort(turretList);

			Turret weakest = turretList.get(0);
			Turret strongest = turretList.get(turretList.size()-1);

			//공격 시간 최신화
			attackTurn[weakest.row][weakest.col] = round;
			//공격포탑 공격력 증가
			board[weakest.row][weakest.col] += N + M;
			//방문 배열 초기화
			visited = new boolean[N][M];
			minRoute = new ArrayList<>();
			times = new int[N][M];

			for(int i=0;i<times.length;i++) {
				Arrays.fill(times[i], Integer.MAX_VALUE);
			}
			//시작 위치 시간 초기화
			times[weakest.row][weakest.col] = 0;

			//레이저 공격
			doLaserAttack(weakest,strongest);

			//레이저 공격의 최단 경로가 없는 경우 포탑 공격
			if(minRoute.size() == 0
				|| minRoute.get(minRoute.size()-1).row != strongest.row
				|| minRoute.get(minRoute.size()-1).col != strongest.col) {
				doCannonAttack(weakest,strongest);
			}

			//공격 대상 포탑 처리
			setTurretAttack(weakest,strongest);

			//포탑 정비
			repairTurret(weakest);
		}

		List<Turret> turretList = findAllTurrets();

		//가장 약한/강한 포탑을 선택하기 위해 포탑 목록 정렬
		Collections.sort(turretList);

		Turret strongest = turretList.get(turretList.size()-1);

		System.out.println(strongest.attack);
	}
	public static void setTurretAttack(Turret attacker,Turret defender) {
		//경로상 포탑 공격력 설정

		for(int i=0;i<minRoute.size();i++) {
			Coordinate curr = minRoute.get(i);

			visited[curr.row][curr.col] = true;
			//공격자인 경우
			if(curr.row == attacker.row && curr.col == attacker.col) {
				continue;
			}
			//공격 대상 포탑인 경우
			else if(curr.row == defender.row && curr.col == defender.col) {
				board[curr.row][curr.col] = Math.max(0,
					board[curr.row][curr.col] - board[attacker.row][attacker.col]);
			}
			//경로상 포탑인 경우
			else {
				board[curr.row][curr.col] = Math.max(0,
					board[curr.row][curr.col] - board[attacker.row][attacker.col]/2);
			}
		}
	}
	public static void repairTurret(Turret attacker) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				//공격 포탑 좌표인 경우
				if(attacker.row == i && attacker.col == j) {
					continue;
				}
				//공격받은 포탑인 경우
				if(visited[i][j]) {
					continue;
				}
				//부서진 포탑인경우
				if(board[i][j] == 0) {
					continue;
				}
				board[i][j]++;
			}
		}
	}

	public static void doCannonAttack(Turret attacker,Turret defender) {
		int[] dy = {-1,-1,-1,0,0,0,1,1,1};
		int[] dx = {-1,0,1,-1,0,1,-1,0,1};

		for(int k=0;k<dy.length;k++) {
			int nextRow = (dy[k] + defender.row + N) % N;
			int nextCol = (dx[k] + defender.col + M) % M;

			//이미 부서진 포탑이면 탐색 X
			if(board[nextRow][nextCol] == 0) {
				continue;
			}

			minRoute.add(new Coordinate(
				nextRow,
				nextCol));
		}
	}
	public static void doLaserAttack(Turret attacker,Turret end) {
		//    	System.out.println(route.size()+" start "+ curr);
		//우 > 하 > 상 > 좌 우선순위 적용
		int[] dy = {0,1,0,-1};
		int[] dx = {1,0,-1,0};
		int endTime = Integer.MAX_VALUE;
		//좌표 방문 처리
		Queue<Laser> pq = new LinkedList<>();

		pq.add(new Laser(
			attacker.row,
			attacker.col,
			0
		));

		while(!pq.isEmpty()) {
			Laser curr = pq.poll();

			// System.out.println(curr.row+" , "+curr.col +" time : "+curr.time);

			if(endTime < curr.time){
				break;
			}

			if(times[curr.row][curr.col] < curr.time) {
				continue;
			}

			//목표 좌표까지 도착한 경우
			if(curr.row == end.row && curr.col == end.col) {
				Laser temp = curr;

				//역으로 올라가면서 경로 탐ㅅ맥
				while(temp !=null){
					//앞으로 값 넣기
					minRoute.add(0,temp);
					temp = temp.prev;
				}
				break;
			}
			//다음 좌표로 이동
			for(int k=0;k<dy.length;k++) {
				Coordinate next = new Coordinate(
					(curr.row + dy[k] + N) % N,
					(curr.col + dx[k]+ M) % M
				);
				//부서진 포탑인경우
				if(board[next.row][next.col] <= 0) {
					continue;
				}
				//최단거리가 아닌 경우
				if(times[next.row][next.col] < curr.time + 1) {
					continue;
				}

				//최단 시간 계산
				times[next.row][next.col] = curr.time + 1;
				Laser laser = new Laser(
					next.row,
					next.col,
					curr.time+1);
				laser.prev = curr;
				//다음 좌표 넣기
				pq.add(laser);
			}
		}

	}

	public static List<Turret> findAllTurrets() {
		List<Turret> turretList = new ArrayList<>();

		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){

				//포탑이라면 포탑 생성
				if(board[i][j] > 0) {
					turretList.add(new Turret(
						new Coordinate(i,j),
						board[i][j],
						attackTurn[i][j]));
				}
			}
		}

		return turretList;
	}

	public static void init() throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);

		board = new int[N][M];
		attackTurn = new int[N][M];

		for(int i=0;i<N;i++){
			input = br.readLine().split(" ");
			for(int j=0;j<M;j++){
				board[i][j] = Integer.parseInt(input[j]);
			}
		}
	}
}

class Laser extends Coordinate implements Comparable<Laser>{
	Laser prev;
	int time;

	public Laser(int row, int col, int time) {
		super(row, col);
		this.time = time;
	}

	@Override
	public int compareTo(Laser o) {

		return this.time - o.time;
	}

}

class Coordinate{
	int row;
	int col;

	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}

	public int sum() {
		return this.row + this.col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}


}

class Turret extends Coordinate implements Comparable<Turret>{
	int attack;
	int attackTurn;

	public Turret(Coordinate coord, int attack, int attackTurn){
		super(coord.row, coord.col);
		this.attack = attack;
		this.attackTurn = attackTurn;
	}

	@Override
	public int compareTo(Turret o) {
		if(this.attack == o.attack) {
			if(this.attackTurn == o.attackTurn) {
				if(this.sum() == o.sum()) {
					return o.col - this.col;
				}

				return  o.sum() - this.sum();
			}

			return o.attackTurn - this.attackTurn;
		}

		return this.attack - o.attack;
	}

	@Override
	public String toString() {
		return "Turret [attack=" + attack + ", attackTurn=" + attackTurn + ", row=" + row + ", col=" + col + "]";
	}
}
