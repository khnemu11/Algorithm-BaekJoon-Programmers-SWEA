import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int FALSE = 0;
    static final int TRUE = 1;
    static final int WALL = 1;
    static final int GRAM = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int minTime = Integer.MAX_VALUE;


        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[2][N][M];

        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<Hero> pq = new PriorityQueue<>();
        pq.add(new Hero(0,0,FALSE,0));

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(!pq.isEmpty()){
            Hero hero = pq.poll();

            if(hero.row == N-1 && hero.col == M-1){
                minTime = Math.min(hero.time,minTime);
                continue;
            }

            visited[hero.gram][hero.row][hero.col] = true;

            for(int k=0;k<dx.length;k++){
                Hero next = new Hero(hero.row + dx[k], hero.col+dy[k], hero.gram,hero.time+1);

                if(next.row <0 || next.row >=N || next.col <0 || next.col>=M){
                    continue;
                }
                if(visited[next.gram][next.row][next.col]){
                    continue;
                }
                if(map[next.row][next.col] == WALL){
                    if(next.gram == FALSE){
                        continue;
                    }
                    visited[next.gram][next.row][next.col] = true;
                    pq.add(next);
                }else if(map[next.row][next.col] == GRAM){
                    next.gram = TRUE;
                    visited[next.gram][next.row][next.col] = true;
                    pq.add(next);
                }else{
                    visited[next.gram][next.row][next.col] = true;
                    pq.add(next);
                }
            }
        }

        if(minTime > T){
            System.out.println("Fail");
        }else{
            System.out.println(minTime);
        }
    }
}

class Hero implements Comparable<Hero>{
    int row;
    int col;
    int gram;
    int time;

    public Hero(int row, int col,int gram, int time){
        this.row = row;
        this.col = col;
        this.gram = gram;
        this.time = time;
    }

    @Override
    public int compareTo(Hero o) {
        return this.time - o.time;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "row=" + row +
                ", col=" + col +
                ", gram=" + gram +
                ", time=" + time +
                '}';
    }
}
