/*
    이동 방향 : 상하좌우
    
    이동 우선 순위 :출구와 거리가 가까움 > 상/하 > 좌/우
    참가자가 출구에 도달하면 탈출

    모든 판이 끝나면 출구와 참가자를 최소 1명을 포함하는 가장 작은 정사각형을 만듬
    정사각형 우선순위
    변의 길이가 가장 작은 값 > 좌상단 좌표의 row가 작은 값
     > 좌상단의 좌표의 col값이 작은 값

    출구와 모든 참가자 1명으로 이루어진 정사각형의 변의 길이 탐색
    만약 변의 길이가 현재 정사각형보다 큰 경우 패스
    작은경우 출구를 기준으로 좌상 > 우상 > 좌하 > 우하 순서로 정사각형이 가능한지 판단하고 
    최소 정사각형 파악


    90도로 회전 후 벽의 내구도 감소
    
    좌상단 : (1,2)
    (1,0) ->(0,1)
    (2,2) ->(1,3) 길이 3

    maze[leftTop.row + i][leftTop.col + j] 
    = maze[leftTop.row+j][letTop.col + rowLength - i - 1]

    출력값 :참가자들의 이동거리 합 및 출구 좌표

*/

import java.util.*;
import java.io.*;

public class Main {
	static int INIT = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(
			new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]); //미로크기
		int M = Integer.parseInt(input[1]); //참가자 수 
		int K = Integer.parseInt(input[2]); //게임 시간 

		//load the maze

		int[][] maze = new int[N][N];

		for(int i=0;i<N;i++){
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++){
				maze[i][j] = Integer.parseInt(input[j]);
			}
		}

		//load users

		Queue<User> userQ = new LinkedList<>();

		for(int i=0;i<M;i++){
			input = br.readLine().split(" ");
			Coordinate coord = new Coordinate(
				Integer.parseInt(input[0])-1,
				Integer.parseInt(input[1])-1
			);

			userQ.add(new User(coord,0,INIT,INIT));
		}

		//load exit
		input = br.readLine().split(" ");
		Coordinate exit = new Coordinate(
			Integer.parseInt(input[0])-1,
			Integer.parseInt(input[1])-1
		);

		// printArr(maze);
		// System.out.println(userQ);
		// System.out.println(exit);

		int totalWalkCount = 0;
		int[] dy = {-1,1,0,0};
		int[] dx = {0,0,-1,1};
		int[] rectY = {-1,-1,1,1};
		int[] rectX = {-1,1,-1,1};

		for(int round=1;round<=K;round++){
			// System.out.println("===="+round+"round start ====");
			//더이상 유저가 없는경우
			if(userQ.isEmpty()){
				// System.out.println("Game end");
				break;
			}
			//move user
			int userCount = userQ.size();
			
			while(userCount-->0){
				User user = userQ.poll();
				PriorityQueue<User> nextUserQ = new PriorityQueue<>();
				//calculate distance with exit and user
				int distance = Math.abs(exit.row - user.coord.row)
					+ Math.abs(exit.col - user.coord.col);

				user.distance = distance;
				
				nextUserQ.add(user);
				//move user
				for(int k=0;k<dy.length;k++){
					Coordinate next = new Coordinate(
						user.coord.row + dy[k],
						user.coord.col + dx[k]);

					//case 1 : out of map
					if(next.row < 0 || next.col<0 || next.row >=maze.length
						|| next.col >= maze[0].length){
						continue;
					}
					//case 2 : wall
					if(maze[next.row][next.col] > 0 ){
						continue;
					}
					//calculate distance with exit and next user 
					distance = Math.abs(exit.row - next.row)
						+ Math.abs(exit.col - next.col);

					User nextUser = new User(
						next,
						user.walkCount+1,
						k,
						distance
					);

					nextUserQ.add(nextUser);
				}
				User nextUser = nextUserQ.poll();
				// System.out.println("next user :" + nextUser);
				//exit user
				if(nextUser.coord.row == exit.row
					&& nextUser.coord.col == exit.col){
					totalWalkCount += nextUser.walkCount;
					continue;
				}
				userQ.add(nextUser);
			}
			// System.out.println("==== Result of user moved ====");  
			// System.out.println(userQ);
			// System.out.println("==== find min-rect start====");

			//find min-rect
			userCount = userQ.size();
			if(userCount == 0){
				break;
			}
			PriorityQueue<Rect> rectQ = new PriorityQueue<>();

			//유저의 좌표를 통해 모든 정사각형 구하기
			while(userCount-->0){
				User user = userQ.poll();
				//get rectangle length
				int rectLength = Math.max(
					Math.abs(user.coord.row - exit.row) + 1,
					Math.abs(user.coord.col - exit.col) + 1
				);
				
				//좌상단 좌표 탐색
				Coordinate leftTop = new Coordinate(
					Math.min(user.coord.row , exit.row),
					Math.min(user.coord.col , exit.col)
				);
	
				//우하단 좌표 탐색
				Coordinate rightBottom = new Coordinate(
					Math.max(user.coord.row , exit.row),
					Math.max(user.coord.col , exit.col)
				);
				
				//set leftTop row
				while(rightBottom.row - leftTop.row + 1 < rectLength){
					if(leftTop.row - 1 >=0){
						leftTop.row--;
					}else{
						rightBottom.row++;
					}
				}
				//set leftTop row
				while(rightBottom.col - leftTop.col + 1 < rectLength){
					if(leftTop.col - 1 >=0){
						leftTop.col--;
					}else{
						rightBottom.col++;
					}
				}
				// System.out.println("leftTop : "+leftTop+" length :"+rectLength);
				rectQ.add(new Rect(leftTop,rectLength));
				userQ.add(user);
			}
			//최소 정사각형 구하기
			Rect minRect = rectQ.poll();
			//rotate maze

			int temp[][] = new int[maze.length][maze[0].length];

			//rotate temp
			for(int i=0;i<minRect.length;i++){
				for(int j=0;j<minRect.length;j++){
					temp[j+minRect.leftTop.row][minRect.leftTop.col+minRect.length - i - 1]
						= maze[i+minRect.leftTop.row][j+minRect.leftTop.col];
					if(temp[j+minRect.leftTop.row][minRect.leftTop.col+minRect.length - i - 1] > 0){
						temp[j+minRect.leftTop.row][minRect.leftTop.col+minRect.length - i - 1]--;
					}
				}
			}
			
			//save temp value in maze
			for(int i=0;i<minRect.length;i++){
				for(int j=0;j<minRect.length;j++){
					maze[i+minRect.leftTop.row][j+minRect.leftTop.col] =
						temp[i+minRect.leftTop.row][j+minRect.leftTop.col];
				}
			}
			// printArr(maze);
			// System.out.println("==== rotate maze end ====");
			//rotate user
			userCount = userQ.size();

			while(userCount-->0){
				User user = userQ.poll();
				Coordinate rightBottom
					= new Coordinate(
					minRect.leftTop.row + minRect.length - 1,
					minRect.leftTop.col + minRect.length - 1
				);
				// case 1 :user is contained the min rect
				if(minRect.leftTop.row <= user.coord.row
					&& rightBottom.row >= user.coord.row
					&& minRect.leftTop.col <= user.coord.col
					&& rightBottom.col >= user.coord.col){
					// System.out.println("before rotate user :"+user.toString());
					user.coord.rotate(minRect);
					// System.out.println("after rotate user :"+user.toString());
				}

				userQ.add(user);
			}

			//rotate exit
			exit.rotate(minRect);
			// System.out.println("rotate exit : "+exit.toString());
			// System.out.println("===="+round+"round end ====");
		}


		while(!userQ.isEmpty()){
			User user = userQ.poll();

			totalWalkCount +=user.walkCount;
		}

		System.out.println(totalWalkCount);
		System.out.println((exit.row+1)+" "+(exit.col+1));
	}

	public static void printArr(int maze[][]){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[i].length;j++){
				System.out.print(maze[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class User implements Comparable<User>{
	Coordinate coord;
	int walkCount;
	int direction;
	int distance;

	public User(Coordinate coord, int walkCount, int direction,int distance){
		this.coord = coord;
		this.walkCount = walkCount;
		this.direction = direction;
		this.distance = distance;
	}

	@Override
	public int compareTo(User o){
		if(this.distance == o.distance){
			return this.direction - o.direction;
		}
		return this.distance - o.distance;
	}

	public String toString(){
		return coord.toString()+" walkCnt : "+this.walkCount+
			" direction : "+this.direction;
	}
}

class Rect implements Comparable<Rect> {
	Coordinate leftTop;
	int length;

	public Rect(Coordinate leftTop, int length){
		this.leftTop = leftTop;
		this.length = length;
	}

	@Override
	public int compareTo(Rect o){
		if(this.length == o.length){
			if(this.leftTop.row == o.leftTop.row){
				return this.leftTop.col - o.leftTop.col;
			}

			return this.leftTop.row - o.leftTop.row;
		}

		return this.length - o.length;
	}

	@Override
	public String toString(){
		return leftTop.toString() + " length : "+length;
	}
}

class Coordinate{
	int row;
	int col;

	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}

	public String toString(){
		return "( "+row+" , " +col+" )";
	}

	//90도 돌리기
	public void rotate(Rect rect){
		int originalRow = this.row - rect.leftTop.row;
		int originalCol = this.col- rect.leftTop.col;

		this.row = rect.leftTop.row + originalCol;
		this.col = rect.leftTop.col + rect.length - originalRow - 1;
	}
}
