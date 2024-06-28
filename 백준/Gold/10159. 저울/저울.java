import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int INF = 100000;

        int[][]distance = new int[N+1][N+1];
        int[][]reverseDistance = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            Arrays.fill(distance[i],INF);
            Arrays.fill(reverseDistance[i],INF);
        }

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            distance[start][end] = 1;
            reverseDistance[end][start] = 1;
        }

        for(int mid=1;mid<=N;mid++){
            for(int start=1;start<=N;start++){
                for(int end=1;end<=N;end++) {
                    if (distance[start][end] > distance[start][mid] + distance[mid][end]) {
                        distance[start][end] = distance[start][mid] + distance[mid][end];
                    }
                    if (reverseDistance[start][end] > reverseDistance[start][mid] + reverseDistance[mid][end]) {
                        reverseDistance[start][end] = reverseDistance[start][mid] + reverseDistance[mid][end];
                    }
                }
            }
        }

        boolean[][]visited = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++){
            for(int end=1;end<=N;end++){
                if(distance[i][end] != INF){
                    visited[i][end] = true;
                }
                if(reverseDistance[end][i] != INF){
                    visited[end][i] = true;
                }
            }
        }

        for(int i=1;i<=N;i++){
            int count = 0;

            for(int end=1;end<=N;end++){
                if(end != i && !visited[i][end]){
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}
