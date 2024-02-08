import java.io.*;
import java.util.*;

public class Main {
    final static char BUILDING = 'x';
    final static char EMPTY = '.';
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        char[][]graph = new char[R][C];

        for(int i=0;i<R;i++){
            graph[i] = br.readLine().toCharArray();
        }

        List<Coordinate> pipeLineList = new ArrayList<>();

        boolean[][] visited = new boolean[R][C];

        //출발점 넣기
        for(int row=0;row<R;row++){
            connect(new Coordinate(row,0),visited,graph);
        }

        System.out.println(count);
    }

    public static boolean connect(Coordinate pipeline,boolean[][]visited,char[][]graph){
        if(pipeline.col == graph[0].length - 1){
            count++;
            return true;
        }

        visited[pipeline.row][pipeline.col] = true;

        int[]dx = {-1,0,1};
        int[]dy = {1,1,1};

        for(int k=0;k<dx.length;k++){
            Coordinate next = new Coordinate(pipeline.row+dx[k],pipeline.col+dy[k]);

            if(next.row<0 || next.col <0 || next.row>=graph.length || next.col>=graph[0].length){
                continue;
            }
            if(graph[next.row][next.col] == BUILDING){
                continue;
            }
            if(visited[next.row][next.col]){
                continue;
            }

            if(connect(next,visited,graph)){
                return true;
            }
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