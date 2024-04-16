import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static Deque<Integer> stack;
    static List<Integer> graph[];
    static boolean[] circle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        stack = new ArrayDeque<>();
        circle = new boolean[N+1];

        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        findCircle(1,1);

        int[] distance = new int[N+1];

        for(int start = 1;start<=N;start++){
            visited = new boolean[N+1];

            Queue<Time> q = new LinkedList<>();
            q.add(new Time(start,0));

            while(!q.isEmpty()){
                Time curr = q.poll();

                if(circle[curr.station]){
                    distance[start] = curr.time;
                    break;
                }

                for(int next : graph[curr.station]){
                    if(visited[next]){
                        continue;
                    }

                    visited[next] = true;
                    q.add(new Time(next,curr.time+1));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++){
            sb.append(distance[i]+" ");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    public static void findCircle(int before, int curr){
        visited[curr] = true;
        stack.addLast(curr);
//        System.out.println(before +" -> "+curr);

        for(int next : graph[curr]){
            if(next == before){
                continue;
            }
            if(circle[next]){
                continue;
            }

            if(visited[next]){
//                System.out.println(curr+"->"+next+" 로 시작된 순환선 찾음");
                while(!stack.isEmpty()){
                    int circleStation = stack.pollLast();
//                    System.out.println(circleStation);
                    circle[circleStation] = true;

                    if(circleStation == next){
                        break;
                    }
                }
            }else{
                findCircle(curr,next);
            }
        }

        if(!stack.isEmpty() && stack.peekLast() == curr){
            stack.pollLast();
        }
    }
}
class Time{
    int station;
    int time;

    public Time(int station, int time){
        this.station = station;
        this.time = time;
    }
}