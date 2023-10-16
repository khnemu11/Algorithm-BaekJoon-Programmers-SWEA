import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 풀이 알고리즘

*
 */
public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static int SEA = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for(int i=0;i< map.length;i++){
			String[] input = br.readLine().split(" ");
			for(int j=0;j<map[i].length;j++){
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		map = divideIsland(map);
		//섬 구분
		// printArr(map);
		//모든 점을 기준으로 다른 섬과 이어지는 다리 구하기

		int minLength = findMinLengthBridge(map);

		System.out.println(minLength);
	}

	public static void printArr(int[][] map){
		for(int i=0;i< map.length;i++){
			for(int j=0;j<map[i].length;j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	//최소 길이의 다리를 찾는 메소드
	public static int findMinLengthBridge(int[][] map){
		int minLength = Integer.MAX_VALUE;

		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				boolean[][] visited = new boolean[map.length][map[0].length];

				//바다인 경우 패스
				if(map[i][j] == SEA){
					continue;
				}
				
				//시작 점
				int start = map[i][j];
				
				//다리 후보 큐
				Queue<Bridge> bridgeQueue = new LinkedList<>();
				bridgeQueue.add(new Bridge(i,j,0));

				//도착하는 모든 섬에게 섬 이름 부여
				while(!bridgeQueue.isEmpty()){
					Bridge curr = bridgeQueue.poll();

					//현재 다리 길이가 최소 길이보다 같거나 큰경우 탐색 불필요
					if(curr.length >= minLength){
						continue;
					}

					visited[curr.row][curr.col] = true;

					//4방향 탐색
					for(int k=0;k<dx.length;k++){
						Coordinate next = new Coordinate(
							curr.row + dx[k],
							curr.col + dy[k]
						);

						//맵 밖으로 나간 경우
						if(isOutOfRange(next, map)){
							continue;
						}

						//이미 방문한 경우
						if(visited[next.row][next.col]){
							continue;
						}

						//출발지 섬인 경우
						if(map[next.row][next.col] == start){
							continue;
						}
						
						//다른 섬에 도착한 경우
						if(map[next.row][next.col] != SEA){
							minLength = Math.min(curr.length,minLength);
							continue;
						}

						//나머지(바다)인경우 방문처리 후 다음 다리로 이어짐
						visited[next.row][next.col]=true;

						bridgeQueue.add(new Bridge(
							next.row,
							next.col,
							curr.length+1
						));
					}
				}
			}
		}


		return minLength;
	}
	
	//섬을 구분하는 메소드
	public static int[][] divideIsland(int[][] map){
		int[][] dividedMap = new int[map.length][map[0].length];
		boolean[][] visited = new boolean[map.length][map[0].length];

		int no = 1; //섬 이름
		
		//섬 구분하기
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				//방문처리
				if(visited[i][j]){
					continue;
				}
				//바다인 경우 섬이 아니므로 탐색 불필요
				if(map[i][j] == SEA){
					continue;
				}
				Queue<Coordinate> islandQueue = new LinkedList<>();
				islandQueue.add(new Coordinate(i,j));

				//도착하는 모든 섬에게 섬 이름 부여
				while(!islandQueue.isEmpty()){
					Coordinate curr = islandQueue.poll();

					visited[curr.row][curr.col] = true;
					dividedMap[curr.row][curr.col] = no;
					
					//4방향 탐색
					for(int k=0;k<dx.length;k++){
						Coordinate next = new Coordinate(
							curr.row + dx[k],
							curr.col + dy[k]
						);

						//맵 밖으로 나간 경우
						if(isOutOfRange(next, map)){
							continue;
						}

						//이미 방문한 경우
						if(visited[next.row][next.col]){
							continue;
						}

						//바다인 경우
						if(map[next.row][next.col] == SEA){
							continue;
						}
						
						//현재 위치를 섬으로 지정
						visited[next.row][next.col]=true;
						dividedMap[next.row][next.col] = no;

						islandQueue.add(next);
					}
				}
				
				//다음 섬 이름 변화
				no++;
			}
		}

		return dividedMap;
	}
	
	//맵 밖으로 나가는지 판단하는 메소드
	public static boolean isOutOfRange(Coordinate coordinate, int[][] map){
		if(coordinate.row <0 || coordinate.col <0 || coordinate.row >=map.length || coordinate.col >= map[0].length){
			return true;
		}

		return false;
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
class Bridge extends Coordinate{
	int length;

	public Bridge(int row, int col,int length){
		super(row,col);
		this.length = length;
	}
}