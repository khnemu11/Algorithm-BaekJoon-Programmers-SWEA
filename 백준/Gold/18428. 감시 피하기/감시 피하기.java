import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Coordinate> students = new ArrayList<>();
    static ArrayList<Coordinate> teachers = new ArrayList<>();
    static int N;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N][N];

        for(int i=0;i<N;i++){
            map[i] = br.readLine().split(" ");

            for(int j=0;j<N;j++){
                if(map[i][j].equals("S")){
                    students.add(new Coordinate(i,j));
                } else if (map[i][j].equals("T")) {
                    teachers.add(new Coordinate(i,j));
                }
            }
        }

        if(find(0)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static boolean find(int cnt){
        if(cnt == 3){
            boolean[][] visited = new boolean[N][N];
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};

            for(Coordinate teacher : teachers){
                for(int k=0; k<dx.length;k++){
                    Coordinate curr = new Coordinate(teacher.row, teacher.col);

                    while(true){
                        Coordinate next = new Coordinate(curr.row + dx[k], curr.col + dy[k]);

                        if(next.row <0 || next.col <0 || next.row >=N || next.col>=N){
                            break;
                        }
                        if(map[next.row][next.col].equals("O")){
                            break;
                        }
                        if(map[next.row][next.col].equals("S")){
                            return false;
                        }

                        visited[next.row][next.col] = true;
                        curr = next;
                    }
                }
            }

            return true;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!map[i][j].equals("X")){
                    continue;
                }

                map[i][j] = "O";

                if(find(cnt+1)){
                    return true;
                }

                map[i][j] = "X";
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
