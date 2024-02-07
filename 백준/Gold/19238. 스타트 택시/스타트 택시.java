import java.io.*;
import java.util.*;

public class Main {
	static final int WALL = 1;
	static final int EMPTY = 0;
	static final int INF = -1;
	
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int N = input[0];	//행
		int M = input[1];	//사람 수
		int K = input[2];	//초기 연료양
		
		int[][] map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //초기 택시 위치
		Taxi taxi = new Taxi(input[0]-1,input[1]-1,K,0);
		
		List<Person> readyPersonList = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			readyPersonList.add(new Person(
					i,
					new Coordinate(input[0]-1,input[1]-1),
					new Coordinate(input[2]-1,input[3]-1),
					0));
		}
		
		while(!readyPersonList.isEmpty() && taxi.fuel >=0) {
			taxi.distance = 0; //이동거리 초기화
			
			PriorityQueue<Person> personPQ = new PriorityQueue<>();
			int[][] minDistance = getMinDistance(map,taxi);
//			System.out.println(Arrays.deepToString(minDistance));
			
			//택시에 태울 승객을 정렬
			for(Person person : readyPersonList) {
				person.distance = minDistance[person.start.row][person.start.col];
				personPQ.add(person);
			}
			
//			System.out.println(personPQ);
			
			Person person = personPQ.poll();
//			System.out.println("승객 : "+person);
			
			taxi.fuel = taxi.fuel - person.distance < 0 ? INF : taxi.fuel - person.distance;
			taxi.row = person.start.row;
			taxi.col = person.start.col;
			taxi.distance = 0;
//			System.out.println("출발지 택시 : "+taxi);
			
			
			if(taxi.fuel == INF) {
				break;
			}
			taxi = move(map,taxi,person.end); //목적기 위치로 택시 이동
//			System.out.println("목적지 택시 : "+taxi);
			
			if(taxi.fuel == INF) {
				break;
			}else {
				taxi.fuel = taxi.fuel + taxi.distance*2;
				readyPersonList = new ArrayList<>(personPQ); //남은 승객을 다음 대기 목록으로 저장
			}
//			System.out.println("다음 출발 택시 : "+taxi);
		}
		
		System.out.println(taxi.fuel);
	}
	public static int[][] getMinDistance(int [][]map, Taxi start) {
		int [] dx = {-1,1,0,0};
		int [] dy = {0,0,-1,1};
		
		int[][] distance = new int[map.length][map[0].length];
		
		for(int i=0;i<distance.length;i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[start.row][start.col] = 0;
		
		//택시로 운반
		PriorityQueue<Taxi> pq = new PriorityQueue<>();
		pq.add(start);
		
		//승객 위치로 이동
		while(!pq.isEmpty()) {
			Taxi curr = pq.poll();
//			System.out.println(curr);
			
			if(distance[curr.row][curr.col] < curr.distance) {
				continue;
			}
			
			for(int i=0;i<dx.length;i++) {
				Taxi next = new Taxi(curr.row+dx[i],curr.col+dy[i],curr.fuel-1,curr.distance+1);
				
				//맵 나가는 것 처리
				if(next.row <0 || next.col <0 || next.row>=map.length || next.col >=map[0].length) {
					continue;
				}
				if(map[next.row][next.col] == WALL) {
					continue;
				}
				if(distance[next.row][next.col] <= distance[curr.row][curr.col] + 1) {
					continue;
				}
				
				distance[next.row][next.col] = distance[curr.row][curr.col] + 1;
				pq.add(next);
			}
		}
		
		return distance;
	}
	public static Taxi move(int[][]map,Taxi start,Coordinate target) {
		int [] dx = {-1,1,0,0};
		int [] dy = {0,0,-1,1};
		
		boolean[][] visited = new boolean[map.length][map[0].length];
		
		//택시로 운반
		PriorityQueue<Taxi> taxiPQ = new PriorityQueue<>();
		taxiPQ.add(start);
		
		//승객 위치로 이동
		while(!taxiPQ.isEmpty()) {
			Taxi curr = taxiPQ.poll();
			
			visited[curr.row][curr.col] = true;
			
			if(curr.row == target.row && curr.col == target.col) {
				return curr;
			}
			
			for(int i=0;i<dx.length;i++) {
				Taxi next = new Taxi(curr.row+dx[i],curr.col+dy[i],curr.fuel-1,curr.distance+1);
				
				//맵 나가는 것 처리
				if(next.row <0 || next.col <0 || next.row>=map.length || next.col >=map[0].length) {
					continue;
				}
				if(map[next.row][next.col] == WALL) {
					continue;
				}
				//방문 처리
				if(visited[next.row][next.col]) {
					continue;
				}
				//기름이 없는 경우 처리
				if(next.fuel < 0) {
					continue;
				}
				
				visited[next.row][next.col] = true;
				taxiPQ.add(next);
			}
		}
		
		return new Taxi(INF,INF,INF,INF);
	}
}

class Person implements Comparable<Person>{
	int seq;
	Coordinate start;
	Coordinate end;
	int distance;
	
	public Person(int seq, Coordinate start, Coordinate end,int distance) {
		this.seq = seq;
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	@Override
	public int compareTo(Person o) {
		if(this.distance == o.distance) {
			if(this.start.row == o.start.row) {
				return this.start.col - o.start.col;
			}
			
			return this.start.row - o.start.row;
		}
		
		return this.distance - o.distance;
	}

	@Override
	public String toString() {
		return "Person [seq=" + seq + ", start=" + start + ", end=" + end + ", distance=" + distance + "]";
	}
	
	
}

class Taxi extends Coordinate implements Comparable<Taxi>{
	int fuel;
	int distance;
	
	public Taxi(int row,int col,int fuel,int distance) {
		super(row,col);
		this.fuel = fuel;
		this.distance = distance;
	}

	@Override
	public int compareTo(Taxi o) {
		return this.distance - o.distance;
	}

	@Override
	public String toString() {
		return "Taxi [fuel=" + fuel + ", distance=" + distance + ", row=" + row + ", col=" + col + "]";
	}

	
}

class Coordinate{
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