import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[]scores = new int[N+1];
        List<Integer> graph[] = new ArrayList[N+1];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(start == -1 && end == -1){
                break;
            }

            graph[start].add(end);
            graph[end].add(start);
        }

        for(int i=1;i<=N;i++){
            Queue<Friend> q = new LinkedList<>();
            q.add(new Friend(i,0));

            boolean[] visited = new boolean[N+1];
            visited[i] = true;

            while(!q.isEmpty()){
                Friend curr = q.poll();

                scores[i] =Math.max(scores[i],curr.count);

                for(int next : graph[curr.people]){
                    if(visited[next]){
                        continue;
                    }
                    visited[next] = true;
                    q.add(new Friend(next,curr.count+1));
                }
            }
        }

        int min = scores[1];
        List<Integer> result = new ArrayList<>();

        for(int i=1;i< scores.length;i++){
            if(min == scores[i]){
                result.add(i);
            }else if(min > scores[i]){
                result = new ArrayList<>();
                min = scores[i];
                result.add(i);
            }
        }

        System.out.println(min+" "+result.size());

        StringBuilder sb = new StringBuilder();

        for(int s : result){
            sb.append(s).append(" ");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }
}

class Friend{
    int people;
    int count;

    public Friend(int people, int count){
        this.people = people;
        this.count = count;
    }
}
