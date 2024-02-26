import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private final static int FORBBIDEN = 0;
    private final static int AVAILABLE = 1;
    private final static int TARGET = 2;
    private final static int UNREACHABLE = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int m = input[1];
        int INF = n*m + 1;

        int[][] map = new int[n][m];
        int[][] minDistance = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(minDistance[i],INF);
        }

        Node start = new Node();

        for(int i=0;i<n;i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int j=0;j<input.length;j++){
                if(input[j] == TARGET) {
                    start = new Node(i, j);
                }
                map[i][j] = input[j];
            }
        }

        minDistance[start.row][start.col] = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(start);

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        while(!q.isEmpty()){
            Node curr = q.poll();

            visited[curr.row][curr.col] = true;

            for(int k=0;k< dx.length;k++){
                Node next = new Node(curr.row + dx[k],curr.col + dy[k]);

                if(next.row <0 || next.col <0 || next.row >=n || next.col >=m){
                    continue;
                }else if(visited[next.row][next.col]){
                    continue;
                }else if(map[next.row][next.col] == FORBBIDEN){
                    continue;
                }else if(minDistance[next.row][next.col] <= minDistance[curr.row][curr.col] + 1){
                    continue;
                }

                minDistance[next.row][next.col] = minDistance[curr.row][curr.col] + 1;
                visited[next.row][next.col] = true;
                q.add(next);
            }
        }

        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();

            for(int j=0;j<input.length;j++){
                if(minDistance[i][j] == INF){
                    if(map[i][j] == FORBBIDEN){
                        sb.append(FORBBIDEN).append(" ");
                    }else{
                        sb.append(UNREACHABLE).append(" ");
                    }
                }else{
                    sb.append(minDistance[i][j]).append(" ");
                }
            }

            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }
    }
}
class Node{
    int row;
    int col;

    public Node(){}

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}