import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    final static char FENCE = '#';
    final static char SHEEP = 'k';
    final static char WOLF = 'v';
    final static char EMPTY = '.';


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<input.length();j++){
                map[i][j] = input.charAt(j);
            }
        }

        boolean[][] visited = new boolean[R][C];
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int totalSheep=0;
        int totalWolf=0;

        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(visited[row][col] || map[row][col] == FENCE){
                    continue;
                }

                Coordinate start = new Coordinate(row,col);
                Queue<Coordinate> q= new LinkedList<>();
                q.add(start);

                int sheep = 0;
                int wolf = 0;

                while(!q.isEmpty()){
                    Coordinate curr = q.poll();
                    visited[curr.row][curr.col] = true;

                    if(map[curr.row][curr.col] == SHEEP){
                        sheep++;
                    }else if(map[curr.row][curr.col] == WOLF){
                        wolf++;
                    }

                    for(int i=0;i<dx.length;i++){
                        Coordinate next = new Coordinate(curr.row + dx[i],curr.col + dy[i]);

                        if(next.row < 0 || next.col <0 || next.row >=R || next.col >=C){
                            continue;
                        }
                        if(map[next.row][next.col] == FENCE){
                            continue;
                        }
                        if(visited[next.row][next.col]){
                            continue;
                        }

                        visited[next.row][next.col] = true;
                        q.add(next);
                    }
                }

                if(sheep > wolf){
                    totalSheep += sheep;
                }else{
                    totalWolf += wolf;
                }
            }
        }


        System.out.println(totalSheep+" "+totalWolf);
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