import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<Node> graph [] = new ArrayList[N+1];
        
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<road.length;i++){
            graph[road[i][0]].add(new Node(road[i][2],road[i][1]));
            graph[road[i][1]].add(new Node(road[i][2],road[i][0]));
        }
        
        int distance[] = new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[1] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,1));
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(distance[curr.seq] < curr.cost){
                continue;
            }
            
            for(Node next : graph[curr.seq]){
                if(distance[next.seq] > distance[curr.seq] + next.cost){
                    distance[next.seq] = distance[curr.seq] + next.cost;
                    
                    pq.add(new Node(distance[next.seq],next.seq));
                }
            }
        }
        
        for(int i=1;i<distance.length;i++){
            if(distance[i] <=K){
                answer++;
            }
        }

        return answer;
    }
}
class Node implements Comparable<Node>{
    int cost;
    int seq;
    
    public Node(int cost, int seq){
        this.cost = cost;
        this.seq = seq;
    }
    
    public int compareTo(Node o){
       return this.cost - o.cost;
    }
}