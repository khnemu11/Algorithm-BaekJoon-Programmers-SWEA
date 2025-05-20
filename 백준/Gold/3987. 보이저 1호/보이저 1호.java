import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<String> answerList = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        String[] directions = {"U","R","D","L"};
        int[] rightShiftDirections = {1,0,3,2};
        int[] leftShiftDirections = {3,2,1,0};
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        SpaceShip start = new SpaceShip(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0,0);
        int maxTime = 0;
        int direction = directions.length;

        for(int i=0;i<directions.length;i++){
            boolean[][][]visited = new boolean[4][N][M];
            SpaceShip curr = new SpaceShip(start.row, start.col,i,0);

            while(true){
                if(curr.row < 0 || curr.col < 0 || curr.row >= N || curr.col >=M){
                    break;
                }

                if(map[curr.row][curr.col] == 'C'){
                    break;
                }

                if(visited[curr.direction][curr.row][curr.col]){
                    curr.time = Integer.MAX_VALUE;
                    break;
                }

                visited[curr.direction][curr.row][curr.col] = true;
                SpaceShip next = new SpaceShip(curr.row + dx[curr.direction], curr.col+ dy[curr.direction],curr.direction,curr.time+1);

                if(map[curr.row][curr.col] == '/'){
                    next = new SpaceShip(curr.row+ dx[rightShiftDirections[curr.direction]], curr.col + dy[rightShiftDirections[curr.direction]],rightShiftDirections[curr.direction],curr.time+1);
                }else if(map[curr.row][curr.col] == '\\'){
                    next = new SpaceShip(curr.row+ dx[leftShiftDirections[curr.direction]], curr.col + dy[leftShiftDirections[curr.direction]],leftShiftDirections[curr.direction],curr.time+1);
                }

                curr = next;
            }

            if(curr.time > maxTime){
                maxTime = curr.time;
                direction = i;
            }
        }

        System.out.println(directions[direction]);
        System.out.println(maxTime == Integer.MAX_VALUE ? "Voyager" : maxTime);
    }
}
class SpaceShip implements Comparable<SpaceShip>{
    int row;
    int col;
    int direction;
    int time;

    public SpaceShip(int row, int col, int direction,int time){
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.time = time;
    }

    @Override
    public int compareTo(SpaceShip o) {
        return this.time == o.time ? o.direction - this.direction : this.time - o.time;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "row=" + row +
                ", col=" + col +
                ", direction=" + direction +
                ", time=" + time +
                '}';
    }
}
