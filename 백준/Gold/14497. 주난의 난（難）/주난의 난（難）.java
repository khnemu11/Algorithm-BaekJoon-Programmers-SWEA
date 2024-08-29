import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Location start = new Location(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0);
        Location target = new Location(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,0);

        char[][]map = new char[N][M];

        for(int i=0;i<N;i++){
            char[]input = br.readLine().toCharArray();

            for(int j=0;j<M;j++){
                map[i][j] = input[j];

                if(map[i][j] == '#'){
                    map[i][j] = '1';
                }else if(map[i][j] == '*'){
                    map[i][j] = '0';
                }
            }
        }

        int[][] minTime = new int[N][M];

        for(int i=0;i<N;i++){
            Arrays.fill(minTime[i],N*M+1);
        }
        minTime[start.row][start.col] = 0;

        PriorityQueue<Location> pq = new PriorityQueue<>((a,b)->a.time - b.time);
        pq.add(start);

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(!pq.isEmpty()){
            Location curr = pq.poll();
            
            if(curr.time > minTime[curr.row][curr.col]){
                continue;
            }

            for(int k=0;k<dx.length;k++){
                Location next = new Location(curr.row + dx[k],curr.col + dy[k],curr.time);

                if(next.row <0 || next.col <0 || next.row>=N || next.col >=M){
                    continue;
                }
                if (map[next.row][next.col] == '1') {
                    next.time++;
                }

                if(minTime[next.row][next.col] <= next.time) {
                    continue;
                }

                minTime[next.row][next.col] = next.time;
                pq.add(next);
            }
        }

        System.out.println(minTime[target.row][target.col]);
    }
}

class Location{
    int row;
    int col;
    int time;

    public Location(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

