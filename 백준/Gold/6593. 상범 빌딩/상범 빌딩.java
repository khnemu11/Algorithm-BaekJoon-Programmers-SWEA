import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[]dl = {-1,0,0,0,0,1};
		int[]dr = {0,-1,1,0,0,0};
		int[]dc = {0,0,0,-1,1,0};

		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());

			int L = Integer.parseInt(st.nextToken()); //층수
			int R = Integer.parseInt(st.nextToken()); //행
			int C = Integer.parseInt(st.nextToken()); //열
			
			//반복 끝
			if(L==0 && R == 0 && C == 0){
				break;
			}

			int [][][]visited = new int[L][R][C];
			char[][][] map = new char[L][R][C];

			for(int i=0;i<L;i++){
				for(int j=0;j<R;j++){
					Arrays.fill(visited[i][j],Integer.MAX_VALUE);
				}
			}

			Coordinate start = new Coordinate();
			Coordinate end = new Coordinate();

			for(int i=0;i<L;i++){
				for(int j=0;j<R;j++){
					String input= br.readLine();
					for(int k=0;k<C;k++){
						map[i][j][k] = input.charAt(k);

						if(map[i][j][k] == 'S'){
							map[i][j][k] ='.';
							start = new Coordinate(i,j,k);
						}else if(map[i][j][k] == 'E'){
							end = new Coordinate(i,j,k);
							map[i][j][k] ='.';
						}
					}
				}
				br.readLine();
			}
			//시작위치 걸린시간 초기화
			visited[start.l][start.r][start.c] = 0;
			
			//시작위치 설정
			Queue<Coordinate> q= new LinkedList<>();
			q.add(start);

			//모든 경우의 수 탐색
			while(!q.isEmpty()){
				Coordinate coord = q.poll();
				
				//끝난경우
				if(map[coord.l][coord.r][coord.c] == 'E'){
					break;
				}
				
				//6방향 탐색
				for(int k=0;k<dl.length;k++){
					Coordinate next = new Coordinate(coord.l + dl[k],coord.r + dr[k],coord.c + dc[k]);
					//층 벗어남
					if(next.l < 0 || next.l >= L){
						continue;
					}
					//행 벗어남
					if(next.r < 0 || next.r >= R){
						continue;
					}
					//열 벗어남
					if(next.c < 0 || next.c >= C){
						continue;
					}
					//시간 증가
					next.time = coord.time+1;
					//탐색이 더 오래걸린 경우 제거
					if(visited[next.l][next.r][next.c] <= next.time){
						continue;
					}
					//벽인 경우 제거
					if(map[next.l][next.r][next.c] == '#'){
						continue;
					}

					visited[next.l][next.r][next.c] =next.time;
					q.add(next);
				}
			}
			if(visited[end.l][end.r][end.c] != Integer.MAX_VALUE){
				bw.write("Escaped in " + visited[end.l][end.r][end.c]+" minute(s).");
			}else{
				bw.write("Trapped!");
			}
			bw.newLine();
		}
		bw.flush();
	}
}

class Coordinate implements Comparable<Coordinate>{
	int l;
	int r;
	int c;
	int time;

	public Coordinate(){}

	public Coordinate(int l, int r, int c){
		this.l=l;
		this.r=r;
		this.c=c;
	}

	@Override
	public int compareTo(Coordinate o) {
		return this.time - o.time;
	}
}
