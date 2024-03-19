import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static boolean[][] foods;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1][M+1];
        foods = new boolean[N+1][M+1];

        Queue<Coordinate> foodQ = new LinkedList<>();

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            foods[r][c] = true;
            foodQ.add(new Coordinate(r,c));
        }

        int maxSize = 0;

        while(!foodQ.isEmpty()){
            Coordinate food = foodQ.poll();

            if(visited[food.row][food.col]){
                continue;
            }

            int size = getSize(food);
            maxSize = Math.max(size,maxSize);
        }

        System.out.println(maxSize);
    }

    public static int getSize(Coordinate start){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);
        int size = 0;

        while(!q.isEmpty()){
            Coordinate curr  = q.poll();
            visited[curr.row][curr.col] = true;
            size++;

            for(int i=0;i<dx.length;i++){
                Coordinate next = new Coordinate(curr.row + dx[i], curr.col+dy[i]);

                if(next.row <1 || next.col <1 || next.row >=foods.length || next.col >=foods[0].length){
                    continue;
                }else if(!foods[next.row][next.col]){
                    continue;
                }else if(visited[next.row][next.col]){
                    continue;
                }

                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        return size;
    }

}