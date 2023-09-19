import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		Integer N = Integer.valueOf(st.nextToken());
		Integer L = Integer.valueOf(st.nextToken());

		Integer[][] map = new Integer[N][N];

		for(int row=0;row<N;row++){
			st = new StringTokenizer(br.readLine());
			for(int col=0;col<N;col++){
				Integer road = Integer.valueOf(st.nextToken());
				map[row][col] = road;
			}
		}

		int count = 0;

		//가로 확인
		for(int row=0;row<N;row++){
			boolean visited[] = new boolean[N]; //이미 경사로로 쓰였는지 확인
			Boolean canRoad = true;
			Integer height = map[row][0];
			for(int col=0;col<N;col++){
				//같은경우
				if(height == map[row][col]){
					continue;
				}
				//높이 차이가 2이상 나는 경우
				if(Math.abs(height - map[row][col]) >1){
					canRoad=false;
					break;
				}
				//이전 높이보다 현재가 큰 경우(이전 위치를 끝으로 경사로 놓기)
				if(height<map[row][col]){
					for(int i=1;i<=L;i++) {
						// System.out.println(col + " vs " + (col - i));
						if (col - i < 0) {
							canRoad = false;
							break;
						}
						if(visited[col-i]){
							canRoad = false;
							break;
						}
						if (map[row][col-1] != map[row][col - i]) {
							canRoad = false;
							break;
						}
						visited[col-i]=true;
					}
				}
				//이전 높이보다 현재가 작은 경우(현재 위치를 시작으로 경사로 놓기)
				else if(height > map[row][col]){
					for(int i=0;i<L;i++){
						// System.out.println(col +" vs "+(col+i));
						if(col + i >=N){
							canRoad = false;
							break;
						}
						if(visited[col+i]){
							canRoad = false;
							break;
						}
						if(map[row][col] != map[row][col+i]){
							canRoad=false;
							break;
						}
						visited[col+i]=true;
					}
				}
				height = map[row][col];
			}

			if(canRoad){
				// System.out.println("가능한 가로 : "+row);
				count++;
			}
		}

		// System.out.println("count : "+count);

		//세로 확인
		for(int col=0;col<N;col++){
			boolean[] visited = new boolean[N];
			Boolean canRoad = true;
			Integer height = map[0][col];
			for(int row=0;row<N;row++){
				//같은경우
				if(height == map[row][col]){
					continue;
				}
				//높이 차이가 2이상 나는 경우
				if(Math.abs(height - map[row][col]) >1){
					canRoad=false;
					break;
				}
				//이전 높이보다 현재가 큰 경우(이전 위치를 끝으로 경사로 놓기)
				if(height<map[row][col]){
					for(int i=1;i<=L;i++) {
						// System.out.println(row + " vs " + (row - i));
						if (row - i < 0) {
							canRoad = false;
							break;
						}
						if(visited[row-i]){
							canRoad=false;
							break;
						}
						if (map[row-1][col] != map[row-i][col]) {
							canRoad = false;
							break;
						}
						visited[row-i]=true;
					}
				}
				//이전 높이보다 현재가 작은 경우(현재 위치를 시작으로 경사로 놓기)
				else if(height > map[row][col]){
					for(int i=0;i<L;i++){
						// System.out.println(row +" vs "+(row+i));
						if(row + i >=N){
							canRoad = false;
							break;
						}
						if(visited[row+i]){
							canRoad=false;
							break;
						}
						if(map[row][col] != map[row+i][col]){
							canRoad=false;
							break;
						}
						visited[row+i]=true;
					}
				}
				height = map[row][col];
			}

			if(canRoad){
				// System.out.println("가능한 세로: "+col);
				count++;
			}
		}

		bw.write(count+"\n");
		bw.flush();
	}
}
