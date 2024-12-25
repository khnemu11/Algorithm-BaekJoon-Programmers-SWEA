import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Coordinate start = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0,0);
        st = new StringTokenizer(br.readLine());
        Coordinate goal = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0,0);

        int[][] map = new int[N+1][M+1];
        int[][][] visited = new int[2][N+1][M+1];

        for(int i=0;i<visited.length;i++){
            for(int j=0;j<visited[i].length;j++){
                Arrays.fill(visited[i][j],M*N+1);
            }
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][start.row][start.col] = 0;
        Queue<Coordinate> q= new LinkedList<>();
        q.add(start);

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(!q.isEmpty()){
            Coordinate curr = q.poll();

            if(curr.row == goal.row && curr.col == goal.col){
                continue;
            }
            if(visited[curr.staff][curr.row][curr.col] < curr.time){
                continue;
            }

            for(int i=0;i<dx.length;i++) {
                Coordinate next = new Coordinate(curr.row + dx[i], curr.col + dy[i], curr.staff, curr.time + 1);

                if (next.row < 1 || next.col < 1 || next.row > N || next.col > M) {
                    continue;
                }
                if(map[next.row][next.col] == 1){
                    if(next.staff == 1){
                        continue;
                    }
                    next.staff = 1;
                }
                if(visited[next.staff][next.row][next.col] <= next.time){
                    continue;
                }

                visited[next.staff][next.row][next.col] = next.time;
                q.add(next);
            }
        }

        int min = Math.min(visited[0][goal.row][goal.col], visited[1][goal.row][goal.col]);
        if(min == M*N+1){
            min = -1;
        }
        System.out.println(min);
    }
}
class Coordinate{
    int row;
    int col;
    int staff;
    int time;

    public Coordinate(int row, int col,int staff,int time){
        this.row = row;
        this.col = col;
        this.staff = staff;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                ", staff=" + staff +
                ", time=" + time +
                '}';
    }
}