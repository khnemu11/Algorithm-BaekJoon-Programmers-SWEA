import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static boolean[][] visited;
    static boolean[][] exit;
    static char[][] map;
    static int N,M;
    static Map<Character,int[]> commandMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        exit = new boolean[N][M];

        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        commandMap.put('U',new int[]{-1,0});
        commandMap.put('R',new int[]{0,1});
        commandMap.put('D',new int[]{1,0});
        commandMap.put('L',new int[]{0,-1});

        for(int row=0;row<N;row++){
            for (int col=0;col<M;col++){
                if(visited[row][col]){
                    continue;
                }

                Coordinate curr = new Coordinate(row,col);
                findExit(curr);
            }
        }

        int count = 0;

        for(int i=0;i<exit.length;i++){
            for(int j=0;j<exit[i].length;j++){
                if(exit[i][j]){
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static boolean findExit(Coordinate curr){
        if(curr.row < 0 || curr.row >= N || curr.col < 0 || curr.col>=M){
            return true;
        }else if(visited[curr.row][curr.col]){
            return exit[curr.row][curr.col];
        }

        visited[curr.row][curr.col] = true;
        Coordinate next = new Coordinate(curr.row + commandMap.get(map[curr.row][curr.col])[0],curr.col + commandMap.get(map[curr.row][curr.col])[1]);
        exit[curr.row][curr.col] = findExit(next);

        return exit[curr.row][curr.col];
    }
}

class Coordinate{
    int row;
    int col;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}