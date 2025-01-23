import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] map = new long[N][N];
        long[][] minDistances = new long[N][N];

        for(int i=0;i<map.length;i++){
            map[i] =  Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            minDistances[i] = Arrays.copyOf(map[i],N);
        }

        for(int mid=0;mid<N;mid++){
            for(int start=0;start<N;start++){
                for(int end=0;end<N;end++){
                    if(minDistances[start][end] > minDistances[start][mid] + minDistances[mid][end]){
                        minDistances[start][end] = minDistances[start][mid] + minDistances[mid][end];
                    }
                }
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if(minDistances[start-1][end-1] > time){
                System.out.println("Stay here");
            }else{
                System.out.println("Enjoy other party");
            }
        }
    }
}
