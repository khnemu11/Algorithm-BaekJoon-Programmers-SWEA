

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int INF = 201 * 10001;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] route = new int[N+1][N+1];
        int[][] distance = new int[N+1][N+1];

        for(int i=0;i<distance.length;i++){
            Arrays.fill(distance[i],INF);
            distance[i][i] = 0;
            route[i][i] = i;
        }

        for(int i=0;i<M;i++){
            input = br.readLine().split(" ");

            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            distance[from][to] = cost;
            distance[to][from] = cost;
            route[from][to] = to;
            route[to][from] = from;
        }
        for(int mid=1;mid<=N;mid++){
            for(int from=1;from<=N;from++){
                for(int to=1;to<=N;to++){
                    if(distance[from][to] > distance[from][mid]+distance[mid][to]){
                        distance[from][to] = distance[from][mid]+distance[mid][to];
                        route[from][to] = mid;
                    }
                }
            }
        }

        for(int i=1;i<route.length;i++){
            StringBuilder sb= new StringBuilder();
            for(int j=1;j<route[i].length;j++){
                if(i == j){
                    sb.append("- ");
                    continue;
                }
                sb.append(getFirst(i,j,route)+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }

    }

    public static int getFirst(int from, int to,int[][] route){
        if(route[from][to] == to){
            return to;
        }

        return getFirst(from,route[from][to],route);
    }
}
class Node implements Comparable<Node>{
    int seq;
    int cost;

    public Node(int seq, int cost){
        this.seq =seq;
        this.cost = cost;
    }

    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}