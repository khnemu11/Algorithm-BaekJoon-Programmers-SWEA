import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int count = 0;
    static int[]table;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        table = new int[N+1];

        for(int i=1;i<=N;i++){
            table[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        List<Integer> result = new ArrayList<>();

        for(int start=1;start<=N;start++){
            Queue<Integer> q = new LinkedList<>();
            q.add(start);

            boolean[] visited = new boolean[N+1];

            while(!q.isEmpty()){
                int curr = q.poll();

                if(visited[curr]){
                    if(curr == start){
                        result.add(curr);
                        count++;
                    }
                    break;
                }

                visited[curr] = true;
                q.add(table[curr]);
            }
        }

        System.out.println(count);

        for(int num : result){
            System.out.println(num);
        }
    }
}