import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int A = 0;
    final static int B = 1;
    static int[] hackNum;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        int N = input[0];
        int M = input[1];

        hackNum = new int[N+1];

        List<Integer>[] graph = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
          graph[i] = new ArrayList();
        }

        for(int i=0;i<M;i++){
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            graph[input[B]].add(input[A]);
        }

        int max = 0;
        List<Integer> result = new ArrayList<>();

        //해킹 가능한 컴퓨터의 수 구하기
        for(int i=1;i<=N;i++){
            int count = 1;

            Queue<Integer> q = new LinkedList<>();
            q.add(i);

            visited = new boolean[N+1];

            while(!q.isEmpty()){
                int curr = q.poll();
                visited[curr]=true;

                for(int next : graph[curr]){
                    if(visited[next]){
                        continue;
                    }
                    visited[next]=true;
                    count++;
                    q.add(next);
                }
            }

            if(count == max){
                result.add(i);
            }else if(count > max){
                result = new ArrayList<>();
                max = count;
                result.add(i);
            }
        }

        for(int val : result){
            System.out.print(val+" ");
        }
    }
}

