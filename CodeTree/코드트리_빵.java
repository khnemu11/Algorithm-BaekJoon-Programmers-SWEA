/*
* 사람들이 가려고 하는 편의점은 모두 다르다
* 
* 이동 우선순위)
* 위 좌 우 하
*
* 베이스 캠프 우선순위)
* 거리가 가까움 > 행이 작음 > 열이 작음
*
* 베이스캠프로 이동하는데 소요 시간은 없음
*
* 지나갈 수 없는 칸)
* 출발한 사람의 베이스캠프
* 사람이 들어간 편의점
*'
* 
* 모두 도착했을 때의 시간
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static int[][] map;
	public static Queue<Coordinate> convenienceQueue; //인덱스의 유저가 가고 싶어하는 편의점 목록
	public static int CANNOT_ARRIVE = -1;
	public static int BASE_CAMP = 1;

	public static void main(String[] args) throws Exception {
		//사람 받기
		init();
		// printArr(map);
		// System.out.println(convenienceQueue);
		int time = 0;

		Queue<People> peopleQueue = new LinkedList<>();
		////사람 움직이는 로직 시작
		while(!convenienceQueue.isEmpty()
			|| !peopleQueue.isEmpty()){
			// System.out.println("====="+time+"번째=====");
			// printArr(map);
			// System.out.println("맵에 있는 사람들");
			// System.out.println(peopleQueue);
			//이동하는 사람들을 상 좌 우 하 순서로 가능한 곳으로 1칸 이동
			List<People> movedPeopleList = movePeople(map,peopleQueue);

			peopleQueue = new LinkedList<>();
			//편의점에 들어간 좌표를 못지나가게 설정
			for(People people : movedPeopleList){
				if(people.row == people.destination.row
					&& people.col == people.destination.col){
					// System.out.println(people+" 도착");
					map[people.row][people.col] = CANNOT_ARRIVE;
					continue;
				}
				peopleQueue.add(people);
			}

			//사람이 있다면 사람을 한명씩 꺼내기
			if(!convenienceQueue.isEmpty()){
				Coordinate convenience = convenienceQueue.poll();
				//사람한테 베이스캠프 지정
				BaseCamp baseCamp = findMinBaseCamp(map,convenience);
				//베이스 캠프를 더이상 이용을 못하도록 함
				map[baseCamp.row][baseCamp.col] = CANNOT_ARRIVE;
				
				//편의점으로 가고있는 사람 만들기
				People people = new People(
					baseCamp.row,
					baseCamp.col,
					convenience
				);
				
				//이동중인 사람 큐에게 새로 등장한 사람 넣기
				peopleQueue.add(people);
			}
			time++;
		}
		////로직 끝
		System.out.println(time);
	}

	public static List<People> movePeople(int[][]map, Queue<People> peopleQueue) throws Exception {
		List<People> nextPeopleQueue = new LinkedList<>();

		while(!peopleQueue.isEmpty()){
			People people = peopleQueue.poll();

			Coordinate nextCoordinate = findNextCoordinate(map,people);
			People nextPeople = new People(
				nextCoordinate.row,
				nextCoordinate.col,
				people.destination
			);

			nextPeopleQueue.add(nextPeople);
		}

		return nextPeopleQueue;
	}
	public static BaseCamp findMinBaseCamp(int[][] map, Coordinate start) throws Exception {
		int[] dx = {-1,0,0,1};
		int[] dy = {0,-1,1,0};
		int minDistance = Integer.MAX_VALUE;

		PriorityQueue<BaseCamp> baseCampPQ = new PriorityQueue<>();
		Queue<Route> routeQueue = new LinkedList<>();
		routeQueue.add(new Route(
			start.row,
			start.col,
			0
		));

		int[][] time = new int[map.length][map[0].length];

		for(int i=0;i<time.length;i++){
			Arrays.fill(time[i], Integer.MAX_VALUE);
		}
		time[start.row][start.col] = 0;

		while(!routeQueue.isEmpty()){
			Route curr = routeQueue.poll();

			if(minDistance < curr.time){
				break;
			}

			if(time[curr.row][curr.col] < curr.time){
				continue;
			}

			//목표 좌표에 도착을 했으면 최소시간(거리) 저장
			if(map[curr.row][curr.col] == BASE_CAMP){
				// System.out.println("베이스캠프 목표 도착");
				// System.out.println(curr);
				baseCampPQ.add(new BaseCamp(curr.row,
					curr.col,
					curr.time
					)
				);
				minDistance = curr.time;
				break;
			}

			for(int k=0;k<dy.length;k++){
				Coordinate next
					= new Coordinate(
					curr.row+dx[k]
					,curr.col+dy[k]
				);

				//맵 밖으로 나간 경우
				if(next.row < 0
					|| next.col < 0
					|| next.row >= map.length
					|| next.col >=map[0].length){
					continue;
				}

				//도착하지 못하는 경우
				if(map[next.row][next.col] == CANNOT_ARRIVE){
					continue;
				}

				//시간이 더 걸리는 경우
				if(time[next.row][next.col] < curr.time + 1){
					continue;
				}

				time[next.row][next.col] = curr.time + 1;
				Route nextRoute = new Route(
					next.row,
					next.col,
					curr.time+1
				);
				nextRoute.prev = curr;
				routeQueue.add(nextRoute);
			}
		}

		return baseCampPQ.poll();
	}
	public static Coordinate findNextCoordinate(int[][] map, People start) throws Exception {
		int[] dx = {-1,0,0,1};
		int[] dy = {0,-1,1,0};
		Coordinate targetNextCoordinate = null;

		Coordinate target = start.destination;

		Queue<Route> routeQueue = new LinkedList<>();
		routeQueue.add(new Route(
			start.row,
			start.col,
			0
		));

		int[][] time = new int[map.length][map[0].length];

		for(int i=0;i<time.length;i++){
			Arrays.fill(time[i], Integer.MAX_VALUE);
		}
		time[start.row][start.col] = 0;

		while(!routeQueue.isEmpty()){
			Route curr = routeQueue.poll();

			if(time[curr.row][curr.col] < curr.time){
				continue;
			}

			//목표 좌표에 도착을 했으면
			if(curr.row == target.row
				&& curr.col == target.col){
				// System.out.println(curr);
				Route temp = curr;

				//이전 경로를 탐색하며 찾으려는 다음 좌표 탐색
				while(temp !=null){
					if(temp.prev == null){
						throw new Exception("에러");
					}
					//다음 좌표를 찾았으면 해당 좌표를 저장
					if(temp.prev.row == start.row
					&& temp.prev.col == start.col){
						targetNextCoordinate = temp;
						break;
					}
					temp = temp.prev;
				}

				break;
			}

			for(int k=0;k<dy.length;k++){
				Coordinate next
					= new Coordinate(
					curr.row+dx[k]
					,curr.col+dy[k]
				);

				//맵 밖으로 나간 경우
				if(next.row < 0
					|| next.col < 0
					|| next.row >= map.length
					|| next.col >=map[0].length){
					continue;
				}
				
				//도착하지 못하는 경우
				if(map[next.row][next.col] == CANNOT_ARRIVE){
					continue;
				}
				
				//시간이 더 걸리는 경우
				if(time[next.row][next.col] < curr.time + 1){
					continue;
				}

				time[next.row][next.col] = curr.time + 1;
				Route nextRoute = new Route(
					next.row,
					next.col,
					curr.time+1
				);
				nextRoute.prev = curr;
				routeQueue.add(nextRoute);
			}
		}

		return targetNextCoordinate;
	}
	public static void printArr(int[][] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int mapSize = Integer.parseInt(input[0]);
		int userNum = Integer.parseInt(input[1]);

		map = new int[mapSize][mapSize];

		for(int i=0;i<mapSize;i++){
			input = br.readLine().split(" ");
			for(int j=0;j<mapSize;j++){
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		convenienceQueue = new LinkedList<>();

		for(int i=0;i<userNum;i++){
			input = br.readLine().split(" ");
			convenienceQueue.add(new Coordinate(
				Integer.parseInt(input[0])-1,
				Integer.parseInt(input[1])-1
			));
		}
	}
}

class BaseCamp extends Coordinate implements Comparable<BaseCamp>{
	int distance;

	public BaseCamp(int row, int col){
		super(row, col);
	}

	public BaseCamp(int row, int col, int distance){
		super(row, col);
		this.distance = distance;
	}

	@Override
	public int compareTo(BaseCamp o) {
		if(this.distance == o.distance){
			if(this.col == o.col){
				return this.col - o.col;
			}
			return this.row - o.row;
		}
		return this.distance - o.distance;
	}

	@Override
	public String toString() {
		return "BaseCamp{" +
			"distance=" + distance +
			", row=" + row +
			", col=" + col +
			'}';
	}
}
class Route extends Coordinate{
	Route prev;
	int time;

	public Route(int row, int col,int time){
		super(row, col);
		this.time = time;
	}

	public String toString() {
		return "Route{" +
			"prev=" + prev +
			", row=" + row +
			", col=" + col +
			", time=" + time +
			'}';
	}
}
class People extends Coordinate{
	Coordinate destination;

	public People(int row, int col, Coordinate destination){
		super(row, col);
		this.destination = destination;
	}
}
class Coordinate{
	int row;
	int col;

	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate{" +
			"row=" + row +
			", col=" + col +
			'}';
	}
}
