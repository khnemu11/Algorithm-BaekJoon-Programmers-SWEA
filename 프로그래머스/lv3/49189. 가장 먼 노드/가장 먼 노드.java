import java.util.*;

class Solution {
    int MAX_VALUE = 20_000 * 50_000;
    public int solution(int n, int[][] edge) {
        List<Integer>graph[] = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++){
            graph[i]=new ArrayList<>();
        }
        
        for(int i=0;i<edge.length;i++){
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        int maxDistance = 0;
        int distance[] = new int[n+1];
        
        Arrays.fill(distance,MAX_VALUE);
        distance[1] = 0;
        Node start = new Node(1,0);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            
            if(distance[curr.no] < curr.cost){
                continue;
            }
            
            for(int child :graph[curr.no]){
                if(distance[child] > distance[curr.no]+1){
                    distance[child] = distance[curr.no]+1;
                    maxDistance =Math.max(distance[child],maxDistance);
                    pq.add(new Node(child, distance[child]));
                }
            }
        }
        
        int cnt = 0;
        
        for(int i=1;i<=n;i++){
            if(distance[i] == maxDistance){
                cnt++;
            }
        }
        
        return (int)cnt;
    }
}
class Node implements Comparable<Node>{
    int no;
    int cost;
    
    public Node(int no, int cost){
        this.no = no;
        this.cost = cost;
    }
    
    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}