import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 0;
    static final int SHARK = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};
        int maxSafeDistance = 0;

        for(int row = 0 ; row<N;row++){
            for(int col = 0; col<M;col++) {
                if(map[row][col] == SHARK){
                    continue;
                }

                Space start = new Space(row, col, 0);
                int minSafeDistance = INF;

                boolean[][] visited = new boolean[N][M];
                visited[start.row][start.col] = true;

                Queue<Space> searchQ = new LinkedList<>();
                searchQ.add(start);

                while (!searchQ.isEmpty()) {
                    Space curr = searchQ.poll();

                    if (!curr.equals(start) && map[curr.row][curr.col] == SHARK) {
                        minSafeDistance = curr.distance;
                        break;
                    }

                    for (int i = 0; i < dx.length; i++) {
                        Space next = new Space(curr.row + dx[i], curr.col + dy[i], curr.distance + 1);

                        if (next.row < 0 || next.col < 0 || next.row >= N || next.col >= M) {
                            continue;
                        }
                        if (visited[next.row][next.col]) {
                            continue;
                        }

                        visited[next.row][next.col] = true;
                        searchQ.add(next);
                    }
                }
                maxSafeDistance = Math.max(maxSafeDistance, minSafeDistance);
            }
        }

        System.out.println(maxSafeDistance);
    }
}

class Space{
    int row;
    int col;
    int distance;

    public Space(int row, int col,int distance){
        this.row = row;
        this.col = col;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Space{" +
                "row=" + row +
                ", col=" + col +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Space)) return false;
        Space space = (Space) o;
        return row == space.row && col == space.col && distance == space.distance;
    }
}