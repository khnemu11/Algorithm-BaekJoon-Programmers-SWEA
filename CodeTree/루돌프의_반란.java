import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 풀이 알고리즘
 *
 * P : 산타 총 수
 * N : 맵 크기
 *
 * 좌상단 : ( 1 ,1 )
 *
 * M : 턴 수
 *
 * 거리 = (r1 - c1)^2 + (c1 - c2)^2
 *
 * 1) 루돌프 이동
 *	이동 우선 순위)
 * 	현재 위치에서 가장 가까운 산타 > r좌표가 큰 산타 > c좌표가 큰 산타
 *	8방향 중 해당 산타와 가장 가까운 곳으로 이동
 *
 * 	산타와 충돌)
 * 	이동 방향에서 C만큼 산타를 이동시킴
 * 	산타 점수 +C
 *  산타 기절
 *
 * 2) 산타 이동
 * 기절하거나 맵 밖으로 나가면 이동 X
 * 이동 우선 순위)
 *	루돌프가 가까운 곳 > 상 > 우 > 하 > 좌
 *
 *  루돌프와 충돌)
 *  이동 방향에서 반대 방향으로 D반큼 산타 이동
 *  산타 점수 +D
 *  산타 기절
 *
 * 충돌 시 위치에 산타가 있다면)
 * 해당 산타를 같은 방향을 1칸 밀기
 * 없을때 까지 반복
 */
public class Main {
	static int[] rudolphDx = {-1,-1,-1,0,0,1,1,1};
	static int[] rudolphDy = {-1,0,1,-1,1,-1,0,1};
	static int[] santaDx = {-1,0,1,0,0};
	static int[] santaDy = {0,1,0,-1,0};
	static int[] reverseSantaDx = {1,0,-1,0,0};
	static int[] reverseSantaDy = {0,-1,0,1,0};
	static int[][] map;
	static int[] stun, score;
	static int N,M,C,D,P;
	static int EMPTY = 0;
	static int RUDOLPH = 999;

	public static void main(String[] args) throws Exception {
		init();
		
		//루돌프&산타 박치기 진행
		for(int round =1; round<=M;round++){
			//루돌프 위치 탐색
			Rudolph rudolph = findRudolph(map);
			//모든 산타 위치 탐색
			List<Santa> santaList = findAllSantas(map);
			
			//산타가 맵에 없으면 게임 종료
			if(santaList.isEmpty()){
				break;
			}
			
			//가장 루돌프와 거리가 짧은 산타 탐색
			Santa minSanta = getMinDistanceSanta(rudolph,santaList);
			
			//가장 가까운산타에게 이동할 루돌프 좌표 탐색
			Rudolph nextRudolph = getNearestRudolphWithSanta(rudolph,minSanta);

			//이전 루돌프 위치 삭제
			map[rudolph.row][rudolph.col] = EMPTY;
			
			//산타이 있는경우 충돌
			if(map[nextRudolph.row][nextRudolph.col] != EMPTY){
				Santa crashedSanta = new Santa(
					map[nextRudolph.row][nextRudolph.col],
					nextRudolph.row + rudolphDx[nextRudolph.moveDirection]*C,
					nextRudolph.col + rudolphDy[nextRudolph.moveDirection]*C
				);
				
				//다음 라운드 까지 기절 및 점수 획득
				stun[crashedSanta.seq] = round+1;
				score[crashedSanta.seq] += C;
				
				//산타를 충돌위치로 날리기
				clash(rudolphDx[nextRudolph.moveDirection],
					rudolphDy[nextRudolph.moveDirection],
					crashedSanta,
					map);
			}

			//다음 루돌프 위치 저장
			map[nextRudolph.row][nextRudolph.col] = RUDOLPH;
			rudolph = nextRudolph;

			//모든 산타를 움직이기
			for(int seq =1; seq<=P; seq++){
				//아직 기절 중인 경우
				if(stun[seq] >= round){
					continue;
				}
				
				//해당 번호의 산타 찾기
				Santa santa = findSantaBySeq(seq,map);

				//맵에 없는 경우
				if(santa == null){
					continue;
				}
				
				//가장 우선순위가 높은 산타 찾기
				Santa nextSanta = getHighestPrioritySanta(rudolph, santa);

				//이전 산타 위치 삭제
				map[santa.row][santa.col] = EMPTY;

				//루돌프가 있는경우 충돌
				if(map[nextSanta.row][nextSanta.col] == RUDOLPH){
					Santa crashedSanta = new Santa(
						nextSanta.seq,
						nextSanta.row + reverseSantaDx[nextSanta.moveDirection]*D,
						nextSanta.col + reverseSantaDy[nextSanta.moveDirection]*D
					);

					//다음 라운드 까지 기절
					stun[crashedSanta.seq] = round+1;
					score[crashedSanta.seq] += D;

					//산타을 충돌위치로 날리기
					clash(reverseSantaDx[nextSanta.moveDirection],
						reverseSantaDy[nextSanta.moveDirection],
						crashedSanta,
						map);

				}
				//루돌프가 없으면 그냥 이동
				else{
					map[nextSanta.row][nextSanta.col] = nextSanta.seq;
				}
			}
			
			//맵에 남은 모든 산타 탐색
			santaList = findAllSantas(map);

			//남은 산타 점수 추가
			for(Santa santa : santaList){
				score[santa.seq]++;
			}
		}

		//산타 점수 출력
		StringBuilder sb = new StringBuilder();

		for(int i=1;i<score.length;i++){
			sb.append(score[i]+" ");
		}

		System.out.println(sb.deleteCharAt(sb.length()-1).toString());
	}
	private static Santa getHighestPrioritySanta(Rudolph rudolph, Santa santa) {
		List<Santa> santaList = new ArrayList<>();

		for(int k=0;k< santaDx.length;k++){
			Santa next = new Santa(
				santa.seq,
				santa.row + santaDx[k],
				santa.col + santaDy[k]
			);
			
			//맵 밖인 경우
			if(isOutOfMap(next, map)){
				continue;
			}
			
			//다른 산타가 있는 경우
			else if(map[next.row][next.col] != EMPTY 
				&& map[next.row][next.col] != santa.seq
				&& map[next.row][next.col] != RUDOLPH){
				continue;
			}

			next.distance = getDistance(next,rudolph);
			next.moveDirection = k;

			santaList.add(next);
		}

		Collections.sort(santaList, new Comparator<Santa>() {
			@Override
			public int compare(Santa o1, Santa o2) {
				if(Double.compare(o1.distance,o2.distance) == 0){
					return o1.moveDirection - o2.moveDirection;
				}
				return Double.compare(o1.distance, o2.distance);
			}
		});

		return santaList.get(0);
	}

	private static Rudolph getNearestRudolphWithSanta(Rudolph rudolph, Santa minSanta) {
		List<Rudolph> rudolphList = new ArrayList<>();

		for(int k=0;k< rudolphDx.length;k++){
			Rudolph next = new Rudolph(
				rudolph.row + rudolphDx[k],
				rudolph.col + rudolphDy[k]
				);
			//맵 밖으로 나가면 탐색 종료
			if(isOutOfMap(next, map)){
				continue;
			}
			//거리와 움직인 방향 저장
			next.distance = getDistance(next,minSanta);
			next.moveDirection = k;

			rudolphList.add(next);
		}
		//거리가 가장 짧은 루돌프 탐색
		Collections.sort(rudolphList, new Comparator<Rudolph>() {
			@Override
			public int compare(Rudolph o1, Rudolph o2) {
				return Double.compare(o1.distance, o2.distance);
			}
		});

		return rudolphList.get(0);
	}
	//입력 메소드
	public static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);	//맵  크기
		M = Integer.parseInt(input[1]);	//라운드 수
		P = Integer.parseInt(input[2]);	//총 산타 수
		C = Integer.parseInt(input[3]);	//루돌프의 충돌력
		D = Integer.parseInt(input[4]); //산타의 충돌력

		map = new int[N+1][N+1];	//맵
		score = new int[P+1];	//seq의 점수 배열
		stun = new int[P+1];	//언제까지 기절인지 저장하는 배열

		input = br.readLine().split(" ");

		Rudolph rudolph = new Rudolph(
			Integer.parseInt(input[0]),
			Integer.parseInt(input[1]));

		map[rudolph.row][rudolph.col] = RUDOLPH;

		for(int i=0;i<P;i++){
			input = br.readLine().split(" ");
			Santa santa = new Santa(
				Integer.parseInt(input[0]),
				Integer.parseInt(input[1]),
				Integer.parseInt(input[2]));

			map[santa.row][santa.col] = santa.seq;
		}
	}
	public static Santa getMinDistanceSanta(Rudolph rudolph, List<Santa> santaList){
		for(int i=0;i<santaList.size();i++){
			Santa santa = santaList.get(i);

			santa.distance = getDistance(santa,rudolph);
		}
		
		//거리가 가장 짧은 > 행이 가장 큰 > 열이 가장 큰 산타 탐색
		Collections.sort(santaList, new Comparator<Santa>() {
			@Override
			public int compare(Santa o1, Santa o2) {
				if(Double.compare(o1.distance,o2.distance)== 0){
					if(o1.row == o2.row){
						return o2.col - o1.col;
					}
					return o2.row - o1.row;
				}
				return Double.compare(o1.distance,o2.distance);
			}
		});
		
		//가장 우선순위가 높은 산타 리턴
		return santaList.get(0);
	}
	
	//루돌프를 찾는 메소드
	public static Rudolph findRudolph(int[][] map){
		Rudolph rudolph = null;

		for(int i=1;i<map.length;i++) {
			for (int j = 1; j < map[i].length; j++) {
				if (map[i][j] == RUDOLPH) {
					rudolph = new Rudolph(
						i,
						j
					);

					return rudolph;
				}
			}
		}
		return rudolph;
	}
	
	//번호별 산타를 찾는 메소드
	public static Santa findSantaBySeq(int seq, int[][] map){
		Santa santa = null;

		for(int i=1;i<map.length;i++){
			for(int j=1;j<map[i].length;j++){
				if(map[i][j] == seq){
					santa = new Santa(
						map[i][j],
						i,
						j
					);

					return santa;
				}
			}
		}

		return santa;
	}
	
	//모든 산타를 찾는 메소드
	public static List<Santa> findAllSantas( int[][] map){
		List<Santa> santas = new ArrayList<>();

		for(int i=1;i<map.length;i++){
			for(int j=1;j<map[i].length;j++){
				if(map[i][j] != RUDOLPH && map[i][j] != EMPTY){
					santas.add(new Santa(
						map[i][j],
						i,
						j
					));
				}
			}
		}

		return santas;
	}
	
	//맵 밖으로 나갔는지 판단하는 메소드
	public static boolean isOutOfMap(Coordinate coordinate, int[][] map){
		if(coordinate.row < 1 || coordinate.col < 1
			|| coordinate.row >= map.length || coordinate.col>= map[0].length){
			return true;
		}

		return false;
	}

	public static void clash(int dx, int dy, Santa curr, int[][] map){
		//맵 밖으로 나가면 더이상 충돌X
		if(isOutOfMap(curr,map)){
			return ;
		}
		//빈 산타이면 탐색 종료
		if(curr.seq == EMPTY){
			return ;
		}

		Santa next = new Santa(
			map[curr.row][curr.col],
			curr.row + dx,
			curr.col + dy
		);
		
		//밀려온 산타을 현재 위치에 저장
		map[curr.row][curr.col] = curr.seq;

		//다음 충돌
		clash(dx, dy, next, map);
	}
	
	//좌표간 거리를 계산하는 메소드
	public static double getDistance(Coordinate c1, Coordinate c2){
		return Math.pow(c1.row - c2.row, 2) + Math.pow(c1.col - c2.col, 2);
	}
}
class Coordinate{
	int row;
	int col;

	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}
}

class Rudolph extends Coordinate{
	double distance;
	int moveDirection;

	public Rudolph(int row, int col) {
		super(row, col);
	}
}

class Santa extends Coordinate{
	int seq;
	double distance;
	int moveDirection;

	public Santa(int seq,int row, int col) {
		super(row, col);
		this.seq = seq;
	}
}
