import java.util.*;

class Solution {
    int MAX_VALUE = 20_000 * 50_000; // INF 설정
    
    public int solution(int n, int[][] edge) {
        List<Integer>graph[] = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++){
            graph[i]=new ArrayList<>();
        }
        //양방향 그래프 생성
        for(int i=0;i<edge.length;i++){
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        int maxDistance = 0; // 최대 길이
        int distance[] = new int[n+1]; //1~n번 까지의 최소 길이
        
        Arrays.fill(distance,MAX_VALUE);
        distance[1] = 0;
        Node start = new Node(1,0); //시작점
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);
        
        //다익스트라 알고리즘을 이용하여 1->n까지 최단거리를 구하는 부분
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            
            if(distance[curr.no] < curr.cost){
                continue;
            }
            
            for(int child :graph[curr.no]){
                if(distance[child] > distance[curr.no]+1){
                    distance[child] = distance[curr.no]+1;
                    maxDistance =Math.max(distance[child],maxDistance); //최대 길이 연산
                    pq.add(new Node(child, distance[child]));
                }
            }
        }
        
        int cnt = 0;    //최대 길이의 개수
        
        //최대 길이의 노드의 개수를 구하는 부분
        for(int i=1;i<=n;i++){
            if(distance[i] == maxDistance){
                cnt++;
            }
        }
        
        return cnt;
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