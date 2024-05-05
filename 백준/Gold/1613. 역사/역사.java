import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int inf = Integer.MAX_VALUE/2;
        
        List<Integer> graph[] = new ArrayList[n+1];
        int[][] distance = new int[n+1][n+1];

        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
            Arrays.fill(distance[i],inf);
        }

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());

            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            distance[before][after] = 1;

            graph[before].add(after);
        }

        for(int mid=1;mid<=n;mid++){
            for(int end=1;end<=n;end++){
                for(int start=1;start<=n;start++){
                    if(distance[start][end] > distance[start][mid] + distance[mid][end]){
                        distance[start][end] = distance[start][mid] + distance[mid][end];
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<s;i++){
            st = new StringTokenizer(br.readLine());

            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            if(distance[before][after] != inf){
                sb.append(-1);
            }else if(distance[after][before] != inf){
                sb.append(1);
            }else{
                sb.append(0);
            }

            sb.append("\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
    }
}